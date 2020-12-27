package Controllers;

import API.IExam;
import API.IUser;
import Main.AdminClient;
import Models.Exam;
import Models.Questions;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AdminController {
    @FXML
    private TableView<Exam> tableExam;
    @FXML
    private TableColumn<Exam, String> tenColumn;

    private ObservableList<Exam> examData = FXCollections.observableArrayList();
    private Stage dialogStage;
    public AdminController() {
        examData.addListener(new ListChangeListener<Exam>() {
            @Override
            public void onChanged(Change<? extends Exam> c) {

            }
        });

    }
    @FXML
    public void switchToCreate() throws IOException {
        AdminClient.setRoot("AddExam");
    }
    @FXML
    private void initialize() {
       try {
           IExam iExam = (IExam) Naming.lookup("rmi://localhost:9090/exam");
           examData = FXCollections.observableArrayList(iExam.getExams());
           tableExam.getItems().addAll(examData);

       }catch (RemoteException | NotBoundException | MalformedURLException e){
           e.printStackTrace();
       }
        System.out.println(examData);
        tenColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());

        tableExam.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showQuestionDetails(newValue));
    }

    private void showQuestionDetails(Exam newValue) {
    }
}
