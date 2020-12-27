package Controllers;

import Main.UserClient;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class finishController {
    @FXML
    private Label userName;
    @FXML
    private  Label ngaySinh;
    @FXML
    private  Label diem;

    @FXML
    private void initialize() {
        User user = UserClient.getUser();
        if (user != null) {
            userName.setText(user.getUsername());
            ngaySinh.setText(user.getFullName());

        }
        diem.setText(TestController.kq);

    }
}
