package Controllers;

import API.IExam;
import API.IUser;
import Main.AdminClient;
import Models.Exam;
import Models.Questions;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class AdminController {
    @FXML
    private TableView<Exam> tableExam;
    @FXML
    private TableColumn<Exam, String> tenColumn;
    @FXML
    private  TableColumn<Exam,String> durationColumn;
    @FXML
    private  TableColumn<Exam, LocalDate> startTimeColumn;
    @FXML
    private  TableColumn<Exam,Boolean> checkTimeColumn;
    @FXML
    private  TableColumn<Exam,Boolean> showMathColumn;

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
        tenColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        durationColumn.setCellValueFactory(
                cellData -> cellData.getValue().durationProperty().asString());
        startTimeColumn.setCellValueFactory(
                cellData -> cellData.getValue().startTimeProperty());
        checkTimeColumn.setCellValueFactory(
                cellData -> cellData.getValue().checkTimeProperty());
        addButtonToTableShowMath();
    
        tableExam.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        showQuestionDetails(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void addButtonToTableShowMath() {
        TableColumn<Exam, Void> colBtn = new TableColumn("Điểm");

        Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>> cellFactory = new Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>>() {

            @Override
            public TableCell<Exam, Void> call(TableColumn<Exam, Void> param) {
                final TableCell<Exam, Void> cell = new TableCell<Exam, Void>() {

                    private final Button btn = new Button("Xem");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Exam data = getTableView().getItems().get(getIndex());


                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            Exam data = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event-> {
                                try {
                                    showMath(data);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableExam.getColumns().add(colBtn);
    }


    private void showQuestionDetails(Exam newValue) throws IOException{
        AdminClient.setRoot("addBaiThi");
    }
    private  void  showMath(Exam newExam) throws  IOException{
//        AdminClient.setRoot("quanlydiemsinhvien");

    }
}
