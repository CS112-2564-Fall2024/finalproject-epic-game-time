module com.example.timor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timor to javafx.fxml;
    exports com.example.timor;
    exports Controllers;
    opens Controllers to javafx.fxml;
    exports gameobjects;
    opens gameobjects to javafx.fxml;
    exports gameobjects.enemy;
    opens gameobjects.enemy to javafx.fxml;
}