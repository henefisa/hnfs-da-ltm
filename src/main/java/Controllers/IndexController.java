package Controllers;

import Main.AdminClient;
import Main.UserClient;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class IndexController {

    @FXML
    private Text username;
    @FXML
    private Text fullName;
    @FXML
    private Text birthday;

    public void initialize(){
        User user = UserClient.getUser();
        if(user != null){
            username.setText(user.getUsername());
            fullName.setText(user.getFullName());
            birthday.setText(user.getBirthday().toString());
        }
    }
}
