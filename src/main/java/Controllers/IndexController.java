package Controllers;

import API.ExamImpl;
import API.IExam;
import API.IExamServer;
import Main.AdminClient;
import Main.UserClient;
import Models.Exam;
import Models.Questions;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Exam> examTableView;
    @FXML
    private TableColumn<Exam,String> nameColumn;

    private ObservableList<Exam> examData = FXCollections.observableArrayList();
    @FXML
    private void handleStart(ActionEvent actionEvent) {
        Exam selectedExam = examTableView.getSelectionModel().getSelectedItem();
        if(selectedExam==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Chọn Bài Thi Để Vào Thi");
            alert.showAndWait();
        }else{
            try {
                IExam iExam = new ExamImpl();
                IExamServer iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
                iExamServer.register(selectedExam.getId(), iExam);
                UserClient.setExam_id(selectedExam.getId());
                UserClient.setRoot("Test");
            } catch (NotBoundException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void initialize() {
        User user = UserClient.getUser();
        if (user != null) {
            username.setText(user.getUsername());
            fullName.setText(user.getFullName());
            birthday.setText(user.getBirthday().toString());
        }


        try {
            IExam iExam = (IExam) Naming.lookup("rmi://localhost:9090/exam");
            examData = FXCollections.observableArrayList(iExam.getExams());
            examTableView.getItems().addAll(examData);
        }catch (RemoteException | NotBoundException | MalformedURLException e){
            e.printStackTrace();
        }
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
    }


}
