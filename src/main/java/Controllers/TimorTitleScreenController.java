package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

//Title Scree
public class TimorTitleScreenController {
    @FXML
    Canvas canvas;
    @FXML
    Button startButton;
    @FXML
    Button exitButton;
    //add transparent button function later

    private Stage stage;

    public void initialize() {
        exitButton.setOnAction(this::handleExitButtonClick);
        startButton.setOnAction(this::handleGameStart);
    }

    private void handleExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handleGameStart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game-Screen.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();

            Scene gameplayScene = new Scene(root);
            newStage.setScene(gameplayScene);

            // GameplayController controller = loader.getController();
            // controller.setStage(stage);

//            stage.setScene(gameplayScene);
//            stage.show();
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}