package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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

    private void handleGameStart(ActionEvent event)  {
        System.out.println("Game start");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/timor/Game-Screen.fxml"));

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



                //example 1
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game-Screen.fxml"));
//            Parent root = loader.load();
//
//            Stage newStage = new Stage();
//
//            Scene gameplayScene = new Scene(root);
//            newStage.setScene(gameplayScene);
//
//             GameScreenController controller = loader.getController();
//             controller.setStage(newStage);
//
////            stage.setScene(gameplayScene);
////            stage.show();
//            newStage.show();


}