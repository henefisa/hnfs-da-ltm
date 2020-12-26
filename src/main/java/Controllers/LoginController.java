package Controllers;

import API.IUser;
import Main.UserClient;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void login() {
        try {
            IUser iUser = (IUser) Naming.lookup("rmi://localhost:9090/user");
            User user = iUser.login(username.getText(), password.getText());

            if(user != null){
                UserClient.setUser(user);
                UserClient.setRoot("index");
            }
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToRegister() throws IOException {
        UserClient.setRoot("register");
    }
}
