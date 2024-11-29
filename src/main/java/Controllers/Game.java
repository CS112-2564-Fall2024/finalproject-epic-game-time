package Controllers;

import com.example.timor.GameLogic;
import com.example.timor.TimorMain;
import gameobjects.Armor;
import gameobjects.Player;
import gameobjects.Weapon;
import gameobjects.enemy.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import static gameobjects.LootRandomizer.randomArmorDrop;
import static gameobjects.LootRandomizer.randomWeaponDrop;

public class Game {

   //enumeration to hold the current game state and to be able to switch after the turn is handled
    enum GameState {
        PLAYER_TURN,
        ENEMY_TURN,
        PLAYER_WIN,
        GAME_OVER,
        PLAYER_LOOT_OPTIONS,
        PLAYER_BLOCK,
        PLAYER_ATTACK;
    }


    GameScreenController controller;
    private GameState currentState = GameState.PLAYER_TURN;
    private int currentRoomNumber;
    private int modifierLevel;
    private int modifierCheck;
    static Stage stage;

    Weapon droppedWeapon = null;
    Armor droppedArmor = null;

    Weapon startingWeapon = new Weapon ("Basic Dagger",50, Optional.of(0.0), "white");
    Armor startingArmor =  new Armor ("Basic Cloth", 50, Optional.of(0.0), "white");

    Player player = new Player(10, startingWeapon, startingArmor);
    public Enemy currentEnemy;

    public Game(Canvas canvas, GameScreenController controller) {
        this.controller = controller;
        this.modifierLevel = 0;
        this.currentRoomNumber = 1;
        this.modifierCheck = 5;

        //spawn random enemy
        this.currentEnemy = spawnRandomBasicEnemy();
        setState(GameState.PLAYER_TURN);

        //start the game
        switchTurnOrder();

    }

    public void switchTurnOrder() {
        controller.updateHealthUI();

        switch (currentState) {
            case PLAYER_TURN:
                enableButtons();
                disableLootButton();
                System.out.println("\nChoose an option");
                break;
            case ENEMY_TURN:
                controller.getAttackButton().setDisable(true);
                handleEnemyTurn();
                break;
            case GAME_OVER:
                System.out.println("Game Over!");
                break;
            case PLAYER_LOOT_OPTIONS:
                generateLoot();
                enableLootButton();
                disableButtons();
                controller.updateLootText();
                break;
            case PLAYER_WIN:
                System.out.println("Player Win advancing room");
                resetDrops();
                advanceRoom();
                break;
        }
    }

    public void takePlayerTurn() {
        if (currentState == GameState.PLAYER_ATTACK) {
            player.playerAttack(currentEnemy);
            System.out.println("Player dealt " + player.getEquippedWeapon().getAttackDamage() + " damage");

            // Check if the enemy is defeated
            if (currentEnemy.getEnemyHealth() <= 0) {
                currentState = GameState.PLAYER_LOOT_OPTIONS;
                switchTurnOrder();
            } else {
                // After player's turn, switch to enemy's turn
                currentState = GameState.ENEMY_TURN;
                // Disable the button until the next player turn
                setState(GameState.ENEMY_TURN);
                switchTurnOrder();
            }
        } else if (currentState == GameState.PLAYER_BLOCK) {
            System.out.println("Enemy Attacking");
            player.playerBlock(currentEnemy.getAttackDamage());
            System.out.println(currentEnemy.getName() + " dealt " + player.playerBlockValue(currentEnemy.getAttackDamage()));


            if (player.getPlayerHealthPoints() <= 0) {
                currentState = GameState.GAME_OVER;
            } else {
                setState(GameState.PLAYER_TURN);
                //add a pause to simulate the enemy attack
                PauseTransition pause = new PauseTransition(Duration.seconds(1));  // 1 second pause
                pause.setOnFinished(event -> switchTurnOrder());
                pause.play();
            }
        }
    }


    public void handleEnemyTurn() {
        System.out.println("Enemy Attacking");
        currentEnemy.enemyAttack(player);
        System.out.println(currentEnemy.getName() + " dealt " + currentEnemy.getAttackDamage() + " damage");

        if(player.getPlayerHealthPoints() <= 0) {
            currentState = GameState.GAME_OVER;
            System.out.println("Enemy wins!");
        } else {
            currentState = GameState.PLAYER_TURN;

            PauseTransition pause = new PauseTransition(Duration.seconds(1));  // 1 second pause
            pause.setOnFinished(event -> switchTurnOrder());
            pause.play();
        }
    }


    //sets current enemy based on random number and setting the case to its assigned enemy
    public Enemy spawnRandomBasicEnemy() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(6); // Generates a number from 0 to 5
        Enemy enemy = switch (randomChoice) {
            case 0 -> new AngrySkeleton();
            case 1 -> new BigRat();
            case 2 -> new LittleTweaker();
            case 3 -> new PuppetedArmor();
            case 4 -> new RollingGolem();
            case 5 -> new ShyGuy();
            default ->
                // This is a fallback just in case
                    new AngrySkeleton(); // Default choice
        };

        return enemy;

    }

    public void advanceRoom() {
        this.currentEnemy = spawnRandomBasicEnemy();
        currentRoomNumber++;
        applyModifier();
        increaseDifficulty();
        controller.handleUIUpdates();
        //sets player health back to max health subject to change once a potion feature is added
        player.setPlayerHealthPoints(player.getPlayerMaxHealthPoints());
        currentState = GameState.PLAYER_TURN;
        switchTurnOrder();
    }

    //used to make the dropped loot back to null
    public void resetDrops() {
        this.droppedArmor = null;
        this.droppedWeapon = null;
    }


    //chooses either the armor or weapon and sets it to a random item based upon its rarity
    public void generateLoot() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(2);
        switch (randomChoice) {
            case 0:
                this.droppedWeapon = randomWeaponDrop();
                break;
            case 1:
                this.droppedArmor  = randomArmorDrop();
                break;
        }
    }

    public void applyModifier() {
        //increases the difficulty starting at room 5 and increases every 5 levels
        //updates the room label and updates the corresponding modifier level as it increases

        if(currentRoomNumber >= modifierCheck) {
            modifierLevel++;
            modifierCheck += 5;
            controller.updateDifficultyUI();
        }
    }

    public void increaseDifficulty() {
        //will increase the damage and defense by 5% every 5 stages subject to change after testing
        double increaseDamage = 0.05;
        double increaseDefense = 0.05;
        double increaseHealth = 0.075;

        //calculate the new damage/defense/health only if the modifier level is 1 or greater, based on the current
        //modifier level does a calculation to increase all stats of the enemy

        if (modifierLevel != 0) {
            //Calculate the new levels based on current modifier level
            double newAttack = currentEnemy.getAttackDamage() *  (1 + (increaseDamage * modifierLevel));
            double newDefense = currentEnemy.getDefense() *  (1 + (increaseDefense * modifierLevel));
            double newMaxHealth = currentEnemy.getEnemyMaxHealth() * (1 + (increaseHealth * modifierLevel));

            //set the new values to the enemy stats
            currentEnemy.setAttackDamage((int)newAttack);
            currentEnemy.setDefense((int)newDefense);
            currentEnemy.setEnemyMaxHealth(Math.round(newMaxHealth));
            currentEnemy.setEnemyHealth(Math.round(newMaxHealth));
        }
    }
    //TODO still need to add a page to transition to the end stage screen which will have a return button that '
    //will display rooms cleared and have a return button to bring back the player to the title screen also
    // need to add screen switches from end screen back to title screen and repeat
    public static void showStatsPage() {
        System.out.println("Game start");
        FXMLLoader fxmlLoader = new FXMLLoader(TimorMain.class.getResource("Game-Screen.fxml"));

        try {
            //Load the game screen file
            Parent root = fxmlLoader.load();

            //creates the new scene
            GameScreenController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            // Retrieve the stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //enables loot buttons
    public void enableLootButton() {
        controller.getYesEquipLootButton().setVisible(true);
        controller.getNoEquipLootButton().setVisible(true);
    }

    //disables loot buttons
    public void disableLootButton() {
        controller.getYesEquipLootButton().setVisible(false);
        controller.getNoEquipLootButton().setVisible(false);
    }

    //enable loot buttons
    public void enableButtons() {
        controller.getAttackButton().setVisible(true);
        controller.getBlockButton().setVisible(true);
    }

    //disable loot buttons
    public void disableButtons() {
        controller.getAttackButton().setVisible(false);
        controller.getBlockButton().setVisible(false);
    }

    //Assortment of all the required getters and setters
    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public Weapon getDroppedWeapon() {
        return droppedWeapon;
    }

    public Armor getDroppedArmor() {
        return droppedArmor;
    }

    public int getModifierLevel() {
        return modifierLevel;
    }

    public void setState(GameState newState) {
        currentState = newState;
    }

    public GameState getState() {
        return currentState;
    }

    public int getCurrentRoomNumber() {
        return currentRoomNumber;
    }

    public void setCurrentRoomNumber(int currentRoomNumber) {
        this.currentRoomNumber = currentRoomNumber;
    }

}

//        gc = canvas.getGraphicsContext2D();
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//                startGame();
//
//            }
//        };
    /* Need to begin game after begin button is clicked on title screen
     * (START) player will always go first
     * need to create turned base system that loops until either player or enemy is dead
     *
     * need to take in player input, button click of attack or block
     * somehow connect to controller to read button input
     * update the game ui with respective result, apply damage or block and update game log
     * check for win condition after player turn if enemy health is 0
     * if player win, generate random loot
     * advance to next room
     *
     * need to create enemy attack during their turn, will just choose a random attack for simplicity
     * update game ui, attack and update game log
     * check for losing condition if player health is 0
     * if enemy win return pull up stats page
     * then return to main menu
     * repeat from (START) until player is dead
     *
     *
     *
     * */

        /*
            1.Begin the Game:

        Create a button on your title screen labeled "Begin".
        Add an event listener to the button which will start the game when clicked.

        Turn-Based System 2.
        Define a loop that continues until either the player or the enemy is dead.
        Use a boolean flag (e.g., playerTurn) to track whose turn it is.\

        Handling Player Input: 3


        Create buttons or controls for "Attack" and "Block".
        Implement listeners or event handlers to respond when those buttons are pressed.

        Connect to Controller: 4

        Depending on the platform, you may need specific libraries to read input from a controller (e.g., use JavaFX for a GUI or other libraries for HID).

        Update UI and Game Log: 5

        Update the UI to reflect the new state after each action (attack/block).
        Append records of each turn to a game log.

        Check Conditions and Generate Loot:  6

        If the enemy's health reaches zero, generate random loot for the player.
        Implement logic to advance to the next room or level.

        Enemy Attack Logic: 7

        Randomly select an attack for the enemy when it's their turn.

        Win/Lose Conditions: 8

        If the player's health reaches zero, display the stats page.
        Provide an option to return to the main menu and restart the game.
        */


