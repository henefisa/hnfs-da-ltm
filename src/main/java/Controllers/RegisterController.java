package Controllers;

import API.IUser;
import Main.UserClient;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RegisterController {
    @FXML
    private TextField username;

    @FXML
    private TextField fullName;

    @FXML
    private DatePicker birthday;

    @FXML
    private PasswordField password;

    @FXML
    private Text response;

    @FXML
    private void register() throws IOException {
        User user = new User(username.getText(), fullName.getText(), birthday.getValue(), password.getText());
        boolean success = false;
        try {
            IUser iUser = (IUser) Naming.lookup("rmi://localhost:9090/user");
            success = iUser.register(user);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        if (success) {
            response.setText("Create user success!");
        } else {
            response.setText("Failed to create user!");
        }

    }

    @FXML
    private void switchToLogin() throws IOException {
        UserClient.setRoot("login");
    }
}
