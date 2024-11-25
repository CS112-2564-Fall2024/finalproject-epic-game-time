package Controllers;

import gameobjects.enemy.AngrySkeleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class GameScreenController {
    @FXML
    private Canvas canvas;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label currentDamageLabel;
    @FXML
    private Label currentCritLabel;
    @FXML
    private Label currentBlockLabel;
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

    private Game game;
    private Stage stage;

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
//           game.startGame();

            // Connect UI buttons with their action handlers
            attackButton.setOnAction(this::handleAttackButtonClick);
            blockButton.setOnAction(this::handleBlockButtonClick);
            // Optional: Connect the potion button if it is being used
            // potionButton.setOnAction(this::handlePotionButtonClick);
        }
    }

    @FXML
    private void handleAttackButtonClick(ActionEvent event) {
        if (game != null && game.currentEnemy != null) {
            game.setPlayerAction("Attack");
            System.out.println("Attacking " + game.currentEnemy.getName() + " For " + game.player.getEquippedWeapon().getAttackDamage() + " damage");
        } else {
            System.out.println("Error: Game or currentEnemy is null");
        }
    }

    @FXML
    private void handleBlockButtonClick(ActionEvent event) {
        // Set player action to block
        game.setPlayerAction("Block");
    }

    public void handleUIUpdates(){
        updateHealthUI();
//        enemyNameLocation.setText("Test");
        updateEnemyName();
    }

    public void updateEnemyName() {
        if (game.currentEnemy != null) {
            enemyNameLocation.setText(game.currentEnemy.getName());
        } else {
            enemyNameLocation.setText("No enemy available");
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
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}