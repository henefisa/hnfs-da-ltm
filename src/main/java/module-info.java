module LTMDA {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;
    requires bcrypt;
    requires rt;
    requires jfxrt;

    opens Main to javafx.fxml;
    exports Main;
    opens Controllers to javafx.fxml;
    exports Controllers;
    exports Server;
    exports API;
    exports Models;
}