package com.example.timor;

import Controllers.TimorTitleScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TimorMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimorMain.class.getResource("Timor-title-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TimorTitleScreenController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("TIMOR!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}