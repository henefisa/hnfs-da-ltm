package Controllers;

import API.ExamImpl;
import API.IExam;
import Main.UserClient;
import Models.User;
import API.IExamServer;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class IndexController {
    private IExamServer iExamServer;
    @FXML
    private Text username;
    @FXML
    private Text fullName;
    @FXML
    private Text birthday;

    public void initialize() {
        User user = UserClient.getUser();
        if (user != null) {
            username.setText(user.getUsername());
            fullName.setText(user.getFullName());
            birthday.setText(user.getBirthday().toString());
        }

        try {
            IExam iExam = new ExamImpl();
            iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
            iExamServer.register("123", iExam);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
