package Controllers;

import gameobjects.Player;
import gameobjects.enemy.Enemy;
import javafx.scene.canvas.Canvas;

public class Game {
    GameScreenController controller;
    private boolean playerTurn;
    Player player;
    Enemy currentEnemy;

    public Game(Canvas canvas, GameScreenController controller) {
        this.controller = controller;
        this.playerTurn = true;
        setUpGame();
        startGame();
    }

    public void startGame() {

        while (player.isAlive() && currentEnemy.enemyIsAlive()) {
            if (playerTurn) {
                handlePlayerTurn();
            } else {
                handleEnemyTurn();
            }
            playerTurn = !playerTurn;
            if (!checkGame()) {
                break;
            }
        }
    }

    public boolean checkGame () {
        if (currentEnemy.enemyIsAlive()) {
            showStatsPage();
            return false;
        } else if (player.isAlive()) {
            generateLoot();
            advanceToNextRoom();
            return false;
        }
        return true;
    }

    public static void handlePlayerTurn() {

    }

    public static void handleEnemyTurn() {

    }

    public static void showStatsPage() {

    }

    public static void generateLoot() {

    }

    public static void advanceToNextRoom() {

    }

}
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


