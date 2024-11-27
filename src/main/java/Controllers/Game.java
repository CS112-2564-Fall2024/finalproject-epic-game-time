package Controllers;

import com.example.timor.GameLogic;
import gameobjects.Armor;
import gameobjects.Player;
import gameobjects.Weapon;
import gameobjects.enemy.*;
import javafx.scene.canvas.Canvas;

import java.util.Optional;
import java.util.Random;

public class Game {
    GameScreenController controller;
    private boolean playerTurn;
    private String playerAction;
    private GameLogic logic;
    private boolean isActionComplete;
    private int playerInput;


    Weapon weapon = new Weapon ("dagger",15, Optional.of(0.0), "white");
    Armor armor =  new Armor ("leather", 4, Optional.of(0.0), "white");

    Player player = new Player(10, weapon, armor);
    public Enemy currentEnemy;

    public Game(Canvas canvas, GameScreenController controller) {
        this.controller = controller;
        this.playerTurn = true;
        this.playerAction = "";
        this.isActionComplete = false;
        this.playerInput = 0;

        //spawn random enemy
        this.currentEnemy = spawnRandomBasicEnemy();

        startGame();
    }

    public void startGame() {

        while (player.isAlive() && currentEnemy.enemyIsAlive()) {

            controller.updateHealthUI();



            if (playerTurn) { // handle player action
                //debugging help
                System.out.println("Player Turn: " + playerTurn);
//                handlePlayerTurn();

                //only switch turn if player action complete
                if (isActionComplete) {
                    System.out.println("Action Complete, Switching Turn");
                    playerTurn = false;
                    System.out.println("After switching, Player Turn: " + playerTurn);// Debugging output
                } else {
                    System.out.println("Player is still performing action");
                }

            } else {
                System.out.println("Enemy Turn: " + playerTurn);
                handleEnemyTurn();
                playerTurn = true;
                System.out.println("After switching, Player Turn: " + playerTurn);  // Debugging output
            }

//            playerTurn = !playerTurn;


            if (!checkGame()) {
                break;
            }
        }
    }



    public boolean checkGame () {
        if (currentEnemy.enemyIsAlive()) {
//            showStatsPage();
            return false;
        } else if (player.isAlive()) {
//            generateLoot();
//            advanceToNextRoom();
            return false;
        }
        return true;
    }

    public void handlePlayerTurn() {
        //reset action flag at beginning of turn
        isActionComplete = false;

        if (playerTurn) {
            System.out.println("player turn to act");  // Debugging message

            // Handle player's actions (Attack, Block, etc.)
            if (playerInput == 1) {
                handleAttack();
                isActionComplete = true;
                System.out.println("Player Attacked");
                System.out.println(currentEnemy.getName() + " current health " + currentEnemy.getEnemyHealth() + " / " + currentEnemy.getEnemyMaxHealth());
            } else if (playerInput == 2) {
                handleBlock();
            }

            playerInput = 0;  // Reset player action for next turn


            controller.updateHealthUI();
        }
    }

    public void handleEnemyTurn() {
        currentEnemy.enemyAttack(player);

    }

    public static void showStatsPage() {

    }

    public static void generateLoot() {

    }

    public static void advanceToNextRoom() {

    }

    public void handleAttack() {

        Weapon equippedWeapon = player.getEquippedWeapon();

        if (equippedWeapon != null) {
            // Get damage from weapon
            System.out.println("Attacking " + currentEnemy.getName() + " for " + player.getEquippedWeapon().getAttackDamage() + " damage.");

            // Apply damage to the enemy
            player.playerAttack(currentEnemy);

            System.out.println("Dealt " + player.getEquippedWeapon().getAttackDamage() + " damage to " + currentEnemy.getName());
            // Update UI with new health values
            controller.updateHealthUI();
        } else {
            System.out.println("No weapon equipped! Cannot attack.");
        }
    }
//         TODO implement function for player to block their turn against enemy attack
    public void handleBlock() {
//        player.playerBlock(currentEnemy);
    }

    public String getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(String playerAction) {
        this.playerAction = playerAction;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public boolean getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean getIsActionComplete() {
        return isActionComplete;
    }

    public void setIsActionComplete(boolean isActionComplete) {
        this.isActionComplete = isActionComplete;
    }

    public int getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(int playerInput) {
        this.playerInput = playerInput;
    }

    public Enemy spawnRandomBasicEnemy() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(6); // Generates a number from 0 to 2
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


