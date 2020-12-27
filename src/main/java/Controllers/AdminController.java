package Controllers;

import Main.AdminClient;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminController {
    @FXML
    public void switchToCreate() throws IOException {
        AdminClient.setRoot("AddExam");
    }
}
