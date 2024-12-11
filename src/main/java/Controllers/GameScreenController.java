package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScreenController {
    //all my fxml components for my GUI

    @FXML
    private Canvas canvas;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label currentDamageLabel;
    @FXML
    private Label currentDefenseLabel;
    @FXML
    private ProgressBar playerHealthProgress;
    @FXML
    private ProgressBar enemyHealthProgress;
    @FXML
    private Button attackButton;
    @FXML
    private Button blockButton;
    @FXML
    private Button potionButton;
    @FXML
    private Label enemyNameLocation;
    @FXML
    private ImageView enemyDisplay;
    @FXML
    private Label weaponLabel;
    @FXML
    private Label armorLabel;
    @FXML
    private Label playerHealthLabel;
    @FXML
    private Label enemyHealthLabel;
    @FXML
    private Text lootText;
    @FXML
    private Button yesEquipLootButton;
    @FXML
    private Button noEquipLootButton;
    @FXML
    private ImageView difficultyImageView;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label potionLabel;

    //variables
    private Game game;
    private Stage stage;
    private final Image difficultyImage = new Image(getClass().getResourceAsStream("/images/Skull_Modifier.png"));

    // No-argument constructor needed by FXMLLoader
    public GameScreenController() {
        // No explicit initial setup required here
    }



    @FXML
    public void initialize() {
        // Initialize the game instance only if canvas is available
        if (canvas != null) {
            game = new Game(canvas, this);

            // Set the canvas to focus when the scene is loaded
            canvas.setFocusTraversable(true);
            canvas.requestFocus();

            // Initialize the UI components according to the game state
           handleUIUpdates();

            // Connect UI buttons with their action handlers
            attackButton.setOnAction(this::handleAttackButtonClick);
            blockButton.setOnAction(this::handleBlockButtonClick);
            yesEquipLootButton.setOnAction((this::handleYesEquipLoot));
            noEquipLootButton.setOnAction(this::handleNoEquipLoot);
            potionButton.setOnAction(this::handlePotionClick);

            //set buttons to transparent
            attackButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white;");
            blockButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white;");
            potionButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white;");
            yesEquipLootButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white;");
            noEquipLootButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white;");

            //Set health bars to red
            enemyHealthProgress.setStyle("-fx-accent: red;");
            playerHealthProgress.setStyle("-fx-accent: red;");
        }
    }

    // on attack button click sets the state to player attack and calls the take player turn to handle
    @FXML
    public void handleAttackButtonClick(ActionEvent event) {
        if (game != null && game.currentEnemy != null) {
            game.setState(Game.GameState.PLAYER_ATTACK);
            game.takePlayerTurn();
        }
    }

    //upon block button click sets the game state to player block and calls the playerblock() method to handle
    @FXML
    private void handleBlockButtonClick(ActionEvent event) {
        if(game != null && game.currentEnemy != null) {
            game.setState(Game.GameState.PLAYER_BLOCK);
            game.playerBlock();
        }
    }

    //upon potion click calls the potionHeal method to heal player and then sets it to enemy turn
    public void handlePotionClick(ActionEvent event) {
        //only use potion if potions are available
        if(game.getPotionCount() != 0) {
            game.potionHeal();

            //switch to enemy turn and continue
            game.setState(Game.GameState.ENEMY_TURN);
            game.switchTurnOrder();
        } else {
            System.out.println("No potions");
        }
    }

    //stores all my UI updates
    public void handleUIUpdates(){
        updateHealthUI();
        updateEnemyName();
        updateEnemyPicture();
        updateEquippedGear();
        updateRoomCounter();
        updateCurrentDefense();
        updateDamageCounter();
        lootDisplayReset();
        updatePotionCount();
    }

    //update room label
    public void updateRoomCounter() {
        roomNumberLabel.setText("Room Number: " + game.getCurrentRoomNumber());
    }

    //update player damage label
    public void updateDamageCounter() {
        currentDamageLabel.setText("Damage: " + game.player.getEquippedWeapon().getAttackDamage());
    }

    //update current player defense label
    public void updateCurrentDefense() {
        currentDefenseLabel.setText("Defense: " + game.player.getEquippedArmor().getArmorValue());
    }

    //updates the current enemy name label
    public void updateEnemyName() {
        if (game.currentEnemy != null) {
            enemyNameLocation.setText(game.currentEnemy.getName());
        } else {
            enemyNameLocation.setText("No enemy available");
        }

    }

    //upon the player taking a loot drop displays the name of the loot drop in inventory
    public void updateEquippedGear() {
        weaponLabel.setText(game.player.getEquippedWeapon().getName());
        armorLabel.setText(game.player.getEquippedArmor().getName());

        setInventoryColor();
    }

    //updates the potion label
    public void updatePotionCount() {
        potionLabel.setText("Total: " + game.getPotionCount());
    }

    //gets and displays the current enemy picture
    public void updateEnemyPicture() {
        if (game.currentEnemy != null) {
            enemyDisplay.setImage(game.currentEnemy.getImage());

            //somewhat jank way of trying to get all the images to be sized within the image view box
            //shyguy the only one giving me problems lol, i give up cant figure it out
            enemyDisplay.setPreserveRatio(true);
            enemyDisplay.setFitWidth(650);
            enemyDisplay.setFitHeight(650);
        }
    }

    //this sets the progress bars as well as the label by their current health over their max health
    @FXML
    public void updateHealthUI() {
        // Ensure health progress bars are set correctly with safe checks on game and its properties
        if (game != null && game.player != null && game.currentEnemy != null) {
            playerHealthProgress.setProgress(
                    (double)game.player.getPlayerHealthPoints() / game.player.getPlayerMaxHealthPoints()
            );
            enemyHealthProgress.setProgress(
                    (double)game.currentEnemy.getEnemyHealth() / game.currentEnemy.getEnemyMaxHealth()
            );

            playerHealthLabel.setText(game.player.getPlayerHealthPoints() + "/" + game.player.getPlayerMaxHealthPoints());
            enemyHealthLabel.setText(game.currentEnemy.getEnemyHealth()+ "/" + game.currentEnemy.getEnemyMaxHealth());
        }
    }

    //upon the difficulty scale threshold met at room 10 updates and dsiplays the image at the top of the screen
    public void updateDifficultyUI() {
        difficultyImageView.setImage(difficultyImage);
        difficultyLabel.setText("x" + game.getModifierLevel());
    }

    //checks to make sure the current dropped item is not null and then equips it to the player
    public void handleYesEquipLoot(ActionEvent event) {
        if(game.droppedWeapon != null && game.droppedArmor == null) {
            game.player.equipWeapon(game.droppedWeapon);
            game.setState(Game.GameState.PLAYER_WIN);
            game.switchTurnOrder();

        }  else if (game.droppedArmor != null && game.droppedWeapon == null) {
            game.player.equipArmor(game.droppedArmor);
            game.setState(Game.GameState.PLAYER_WIN);
            game.switchTurnOrder();
        }
    }

    //if player does not take loot then game advances to next room
    public void handleNoEquipLoot(ActionEvent event) {
        game.setState(Game.GameState.PLAYER_WIN);
        game.switchTurnOrder();
    }

    //if a loot item is dropped text is updated and displays stats of the dropped item
    public void updateLootText() {
        if(game.droppedWeapon != null && game.droppedArmor == null) {
            displayDroppedWeapon();
        } else if (game.droppedArmor != null && game.droppedWeapon == null) {
            displayDroppedArmor();
        }

    }

    //method to handle if the loot item is a weapon
    public void displayDroppedWeapon() {
        String rarity = game.droppedWeapon.getRarity();
        System.out.println("Weapon rarity: " + rarity);

        //checks rarity of item and sets the text color that corresponds to its rarity
        switch (rarity) {
            case "white":
                lootText.setFill(Color.WHITE);  // Set text color to white for white rarity
                break;
            case "green":
                lootText.setFill(Color.LIGHTGREEN);  // Set text color to green for green rarity
                break;
            case "red":
                lootText.setFill(Color.RED);  // Set text color to red for red rarity
                break;
            default:
                lootText.setFill(Color.GRAY);  // Default case, set to gray if rarity doesn't match known types
                break;
        }

        //details that are put onto label
        String weaponDetails = ("Weapon Drop" +
                "\nName: " + game.droppedWeapon.getName()
                + "\nAttack Damage: " + game.droppedWeapon.getAttackDamage()
                + "\nCrit Chance: " + game.droppedWeapon.getCritChance());

        lootText.setText(weaponDetails);
    }

    //this is for debugging/testing purposes
    public void displayTextTest() {
        lootText.setText("Hellloooooo");
    }


    //displays armor if that is the loot drop
    public void displayDroppedArmor() {

        String rarity = game.droppedArmor.getRarity();
        System.out.println("Armor rarity: " + rarity);

        //check rarity of item and sets text color that corresponds with rarity
        switch (rarity) {
            case "white":
                lootText.setFill(Color.WHITE);  // Set text color to white for white rarity
                break;
            case "green":
                lootText.setFill(Color.LIGHTGREEN);  // Set text color to green for green rarity
                break;
            case "red":
                lootText.setFill(Color.RED);  // Set text color to red for red rarity
                break;
            default:
                lootText.setFill(Color.GRAY);  // Default case, set to gray if rarity doesn't match known types
                break;
        }

            String armorDetails = ("Armor Drop" +
                    "\nName: " + game.droppedArmor.getName()
            + "\nDefense: " + game.droppedArmor.getArmorValue()
            + "\nBlock Percentage: " + game.droppedArmor.getBlockPercentage());

        lootText.setText(armorDetails);
    }

    //this resets the loot display so that it is an empty string after player decides to take or discard the item that
    //is dropped
    public void lootDisplayReset() {
        lootText.setText("");
    }

    //GETTERS/SETTERS for my buttons
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getAttackButton() {
        return attackButton;
    }

    public Button getYesEquipLootButton() {
        return yesEquipLootButton;
    }

    public Button getNoEquipLootButton() {
        return noEquipLootButton;
    }

    public Button getBlockButton() {
        return blockButton;
    }

    public Button getPotionButton() {
        return potionButton;
    }

    // this is the method that checks the equipped weapon and armor and sets the color of the text to the same
    //of its rarity
    public void setInventoryColor() {
        String weaponRarity = game.player.getEquippedWeapon().getRarity();
        String armorRarity = game.player.getEquippedArmor().getRarity();

        switch (weaponRarity) {
            case "white":
                weaponLabel.setStyle("-fx-text-fill: white;");  // Set text color to white for white rarity
                break;
            case "green":
                weaponLabel.setStyle("-fx-text-fill: lightgreen;");  // Set text color to green for green rarity
                break;
            case "red":
                weaponLabel.setStyle("-fx-text-fill: red;");  // Set text color to red for red rarity
                break;
            default:
                weaponLabel.setStyle("-fx-text-fill: gray;");  // Default case, set to gray if rarity doesn't match known types
                break;
        }

        switch (armorRarity) {
            case "white":
                armorLabel.setStyle("-fx-text-fill: white;");  // Set text color to white for white rarity
                break;
            case "green":
                armorLabel.setStyle("-fx-text-fill: lightgreen;");  // Set text color to green for green rarity
                break;
            case "red":
                armorLabel.setStyle("-fx-text-fill: red;");  // Set text color to red for red rarity
                break;
            default:
                armorLabel.setStyle("-fx-text-fill: gray;");  // Default case, set to gray if rarity doesn't match known types
                break;
        }

    }
}