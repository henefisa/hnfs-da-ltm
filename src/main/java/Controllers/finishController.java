package Controllers;

import Main.UserClient;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class finishController {
    @FXML
    private Label userName;

    @FXML
    private  Label diem;

    @FXML
    private void initialize() {
        User user = UserClient.getUser();
        if (user != null) {
            userName.setText(user.getUsername());


        }
        diem.setText(TestController.kq);

    }

    public void index(ActionEvent actionEvent) {
        try {
            UserClient.setRoot("index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        try {
            UserClient.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
