package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Login {
    private ArrayList<HashMap<String, String>> users = new ArrayList<>();
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void login() throws IOException {
        System.out.println("username is" + username.getText());
        System.out.println("password is" + password.getText());
        boolean hasUser = false;
        users.forEach(user -> {
            if (user.get("username").equals(username.getText()) && user.get("password").equals(password.getText())) {
                System.out.println("Login success!");
            } else {
                System.out.println(("Failed to login!"));
            }
        });
    }

    @FXML
    private void switchToRegister() throws IOException {
//        Main.setRoot("register");
        System.out.println("switched to register");
        HashMap<String, String> user = new HashMap<>();
        user.put("username", username.getText());
        user.put("password", password.getText());
        users.add(user);
    }
}
