module LTMDA {
    requires javafx.controls;
    requires javafx.fxml;

    opens Main to javafx.fxml;
    exports Main;
    opens Controllers to javafx.fxml;
    exports  Controllers;
}