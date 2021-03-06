module LTMDA {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;
    requires bcrypt;

    opens Main to javafx.fxml;
    exports Main;
    opens Controllers to javafx.fxml;
    exports Controllers;
    exports API;
    exports Models;
}