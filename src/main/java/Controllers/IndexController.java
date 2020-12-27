package Controllers;

import API.ExamImpl;
import API.IExam;
import API.IExamServer;
import Main.UserClient;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class IndexController {

    @FXML
    private Text username;
    @FXML
    private Text fullName;
    @FXML
    private Text birthday;

    @FXML
    private void startExam() {
        try {
            IExam iExam = new ExamImpl();
            IExamServer iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
            iExamServer.register("123", iExam); // FIXME: 12/27/2020
            UserClient.setRoot("Test");
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        User user = UserClient.getUser();
        if (user != null) {
            username.setText(user.getUsername());
            fullName.setText(user.getFullName());
            birthday.setText(user.getBirthday().toString());
        }
    }
}
