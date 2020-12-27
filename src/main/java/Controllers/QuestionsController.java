package Controllers;

import API.IExam;
import API.IQuestion;
import DataAccessor.QuestionsDA;
import Main.AdminClient;
import Models.Exam;
import Models.Questions;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;


public class QuestionsController {

    @FXML
    private TextField id;
    @FXML private  TextArea question;
    @FXML private  TextField answer1;
    @FXML private  TextField answer2;
    @FXML private  TextField answer3;
    @FXML private  TextField answer4;
    @FXML private  TextField answer_true;
    private Questions questions;
    private boolean okClicked = false;
    @FXML private Button buttonADD=new Button();
    @FXML
    private TableView<Questions> questionTable;
    @FXML
    private TableColumn<Questions, String> questionColumn;
    @FXML
    private TableColumn<Questions, String> questionTrueColumn;
    @FXML private  TableColumn<Questions,String> questionIndexColumn;
    private ObservableList<Questions> questionData = FXCollections.observableArrayList();
    private ObservableList<Exam> examData = FXCollections.observableArrayList();
    private static String idExam;
    private Stage stage;
    public QuestionsController() {
        questionData.addListener(new ListChangeListener<Questions>() {
            @Override
            public void onChanged(Change<? extends Questions> c) {
            }
        });

    }
    @FXML
    private void initialize()  {
        try {

            QuestionsDA questionsDA=new QuestionsDA();
            IQuestion iQuestion = (IQuestion) Naming.lookup("rmi://localhost:9090/questions");
            questionData = FXCollections.observableArrayList(iQuestion.getQuestionByExamID(AdminController.examId));

            questionTable.getItems().addAll(questionData);
            System.out.println(questionData);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        questionColumn.setCellValueFactory(
                cellData -> cellData.getValue().questionProperty());
        questionTrueColumn.setCellValueFactory((
                cellData -> cellData.getValue().answer_trueProperty()));
        showQuestionDetails(null);
        questionTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showQuestionDetails(newValue));
    }

    private void showQuestionDetails(Questions newValue) {
        if(newValue!=null){
            id.setText(newValue.getId());
            question.setText(newValue.getQuestion());
            answer1.setText(newValue.getAnswer1());
            answer2.setText(newValue.getAnswer2());
            answer3.setText(newValue.getAnswer3());
            answer4.setText(newValue.getAnswer4());
            answer_true.setText(newValue.getAnswer_true());


        }else{
            id.setText("");
            question.setText("");
            answer1.setText("");
            answer2.setText("");
            answer3.setText("");
            answer4.setText("");
            answer_true.setText("");
        }
    }
    public void handleADD(ActionEvent actionEvent)throws IOException {
        UUID uuid = UUID.randomUUID();
        Questions tempPerson = new Questions(question.getText(),answer1.getText(),answer2.getText(),
                answer3.getText(),answer4.getText(),answer_true.getText(),AdminController.examId);
//        questionData.addAll(tempPerson);
        questionTable.getItems().addAll(tempPerson);

        boolean success = false;
        try {
            IQuestion iQuestion = (IQuestion) Naming.lookup("rmi://localhost:9090/questions");
            success = iQuestion.createQuestion(tempPerson);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        if (success) {
            id.clear();
            question.clear();
            answer1.clear();
            answer2.clear();
            answer3.clear();
            answer4.clear();
            answer_true.clear();;
        }
    }
    public void Buttonok(KeyEvent keyEvent){
        if (keyEvent.getCode()== KeyCode.ENTER){
            buttonADD.fire();
            keyEvent.consume();
        }
    }
    public void createQuestion(Exam exam){
        setIdExam(exam.getId());
    }


    public void handleClear(ActionEvent actionEvent) {
        id.setText("");
        question.setText("");
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
        answer_true.setText("");
    }
    @FXML
    private void cancelCreate() throws IOException {
    stage.close();
    }
    public String getIdExam() {
        return idExam;
    }

    public TableView<Questions> getQuestionTable() {
        return questionTable;
    }

    public ObservableList<Questions> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(ObservableList<Questions> questionData) {
        this.questionData = questionData;
    }

    public void setQuestionTable(TableView<Questions> questionTable) {
        this.questionTable = questionTable;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }
}
