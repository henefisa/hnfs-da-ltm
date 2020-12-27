package Controllers;

import API.IExam;
import Main.AdminClient;
import Models.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ExamController {
    @FXML
    private TextField name;
    @FXML
    private  TextField duration;
    @FXML
    private DatePicker startTime;
    @FXML
    private CheckBox checkTime;
    @FXML
    private void createExam() throws IOException{
        Exam exam = new Exam(name.getText(),Integer.parseInt(duration.getText()),startTime.getValue(),checkTime.isSelected());
        boolean success = false;
        try {
            IExam iExam = (IExam) Naming.lookup("rmi://localhost:9090/exam");
            success = iExam.createExam(exam);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        if (success) {
            System.out.println("Create success!");
            AdminClient.setRoot("Admin");
        }
    }

    @FXML
    private void cancelCreate() throws IOException {
        AdminClient.setRoot("Admin");
    }
}
