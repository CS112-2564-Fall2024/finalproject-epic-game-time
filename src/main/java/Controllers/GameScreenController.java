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


    private Game game;
    private Stage stage;
    private final Image difficultyImage = new Image(getClass().getResourceAsStream("/images/Skull_Modifier.png"));

    // No-argument constructor needed by FXMLLoader
    public GameScreenController() {
        // No explicit initial setup required here
    }

    //change the color of progress bars

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

    @FXML
    public void handleAttackButtonClick(ActionEvent event) {
        if (game != null && game.currentEnemy != null) {
            game.setState(Game.GameState.PLAYER_ATTACK);
            game.takePlayerTurn();
        }
    }
    @FXML
    private void handleBlockButtonClick(ActionEvent event) {
        if(game != null && game.currentEnemy != null) {
            game.setState(Game.GameState.PLAYER_BLOCK);
            game.playerBlock();
        }
    }

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

    public void updateRoomCounter() {
        roomNumberLabel.setText("Room Number: " + game.getCurrentRoomNumber());
    }

    public void updateDamageCounter() {
        currentDamageLabel.setText("Damage: " + game.player.getEquippedWeapon().getAttackDamage());
    }

    public void updateCurrentDefense() {
        currentDefenseLabel.setText("Defense: " + game.player.getEquippedArmor().getArmorValue());
    }

    public void updateEnemyName() {
        if (game.currentEnemy != null) {
            enemyNameLocation.setText(game.currentEnemy.getName());
        } else {
            enemyNameLocation.setText("No enemy available");
        }

    }

    public void updateEquippedGear() {
        weaponLabel.setText(game.player.getEquippedWeapon().getName());
        armorLabel.setText(game.player.getEquippedArmor().getName());

        setInventoryColor();
    }

    public void updatePotionCount() {
        potionLabel.setText("Total: " + game.getPotionCount());
    }

    public void updateEnemyPicture() {
        if (game.currentEnemy != null) {
            enemyDisplay.setImage(game.currentEnemy.getImage());

            enemyDisplay.setPreserveRatio(true);
            enemyDisplay.setFitWidth(650);
            enemyDisplay.setFitHeight(650);
        }
    }

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

    public void updateDifficultyUI() {
        difficultyImageView.setImage(difficultyImage);
        difficultyLabel.setText("x" + game.getModifierLevel());
    }

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

    public void handleNoEquipLoot(ActionEvent event) {
        game.setState(Game.GameState.PLAYER_WIN);
        game.switchTurnOrder();
    }

    public void updateLootText() {
        if(game.droppedWeapon != null && game.droppedArmor == null) {
            displayDroppedWeapon();
        } else if (game.droppedArmor != null && game.droppedWeapon == null) {
            displayDroppedArmor();
        }

    }

    public void displayDroppedWeapon() {
        String rarity = game.droppedWeapon.getRarity();
        System.out.println("Weapon rarity: " + rarity);

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


        String weaponDetails = ("Weapon Drop" +
                "\nName: " + game.droppedWeapon.getName()
                + "\nAttack Damage: " + game.droppedWeapon.getAttackDamage()
                + "\nCrit Chance: " + game.droppedWeapon.getCritChance());

        lootText.setText(weaponDetails);
    }

    public void displayTextTest() {
        lootText.setText("Hellloooooo");
    }

    public void displayDroppedArmor() {

        String rarity = game.droppedArmor.getRarity();
        System.out.println("Armor rarity: " + rarity);

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

    public void lootDisplayReset() {
        lootText.setText("");
    }


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