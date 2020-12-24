package Controllers;

import API.ILogin;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.HashMap;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void login() {
        try {
            ILogin iLogin = (ILogin) Naming.lookup("rmi://localhost:9090/login");
            iLogin.Login(username.getText(), password.getText());
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToRegister() throws IOException {
//        Main.setRoot("register");
        System.out.println("switched to register");
        HashMap<String, String> user = new HashMap<>();
        user.put("username", username.getText());
        user.put("password", password.getText());
    }
}
