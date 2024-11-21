package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.awt.event.ActionListener;


public class GameScreenController {
    @FXML
    Canvas canvas;
    @FXML
    Label roomNumberLabel;
    @FXML
    Label currentDamageLabel;
    @FXML
    Label currentCritLabel;
    @FXML
    Label currentBlockLabel;
    @FXML
    ProgressBar playerHealthProgress;
    @FXML
    ProgressBar enemyHealthProgress;
    @FXML
    Button attackButton;
    @FXML
    Button blockButton;
    @FXML
    Button potionButton;

    Game game;

    public GameScreenController(Game game, Canvas canvas, Button attackButton, Button blockButton) {
        this.canvas = canvas;
        this.game = game;
        this.attackButton = attackButton;
        this.blockButton = blockButton;
//        this.potionButton = potionButton;
        initialize();
    }

    //TODO add text log for all info in game in bottom right corner of FXML

    public void initialize() {
        //canvas focues to capture input
        canvas.setFocusTraversable(true);
        canvas.requestFocus();

        //setting up button actions
        attackButton.setOnAction(this:: handleAttackButtonClick);
        blockButton.setOnAction(this:: handleBlockButtonClick);
//        potionButton.setOnAction(this:: handlePotionButtonClick);
    }

    public void handleAttackButtonClick(ActionEvent event) {
        game.setPlayerAction("Attack");
        //maybe need check game logic here
    }

    public void handleBlockButtonClick(ActionEvent event) {
        game.setPlayerAction("Block");
    }

    public void updateHealthUI() {
        playerHealthProgress.setProgress(game.player.getPlayerHealthPoints() / game.player.getPlayerMaxHealthPoints());
        enemyHealthProgress.setProgress(game.currentEnemy.getEnemyHealth() / game.currentEnemy.getEnemyMaxHealth());
    }

//    public void handlePotionButtonClick(ActionEvent event) {
//        game.handlePlayerTurn("Potion");
//    }





}
