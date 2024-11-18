package Controllers;

import com.example.timor.GameLoop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//Title Scree
public class TimorTitleScreenController {
    @FXML
    Canvas canvas;
    @FXML
    Button startButton;
    @FXML
    Button exitButton;
    //add transparent button function later

    public void initialize() {
        exitButton.setOnAction(this::handleExitButtonClick);
        startButton.setOnAction(this::handleGameStart);
    }

    private void handleExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    private void handleGameStart(ActionEvent event) {
    }
}