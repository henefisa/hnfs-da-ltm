package Controllers;

import Main.AdminClient;
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


public class ExamQuestions {

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
    private TableView<Questions> QuestionTable;
    @FXML
    private TableColumn<Questions, String> questionColumn;
    @FXML
    private TableColumn<Questions, String> questionTrueColumn;
    @FXML private  TableColumn<Questions,String> questionIndexColumn;
    private ObservableList<Questions> QuestionData = FXCollections.observableArrayList();
    private Stage dialogStage;
    public ExamQuestions() {
        QuestionData.addListener(new ListChangeListener<Questions>() {
            @Override
            public void onChanged(Change<? extends Questions> c) {
            }
        });

    }
    @FXML
    private void initialize() {
        questionColumn.setCellValueFactory(
                cellData -> cellData.getValue().questionProperty());
        questionTrueColumn.setCellValueFactory((
                cellData -> cellData.getValue().answer_trueProperty()));
        showQuestionDetails(null);
        QuestionTable.getSelectionModel().selectedItemProperty().addListener(
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



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    public void handleADD(ActionEvent actionEvent) {
        questions.setQuestion(question.getText());
        questions.setAnswer1((answer1.getText()));
        questions.setAnswer2(answer2.getText());
        questions.setAnswer3(answer3.getText());
        questions.setAnswer4((answer4.getText()));
        questions.setAnswer_true((answer_true.getText()));
        okClicked = true;
        Questions tempPerson = new Questions();
        if (okClicked) {
//            personData.addAll(tempPerson);
            QuestionData.addAll(tempPerson);
        }
    }
    public void Buttonok(KeyEvent keyEvent){
        if (keyEvent.getCode()== KeyCode.ENTER){
            buttonADD.fire();
            keyEvent.consume();
        }
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
        AdminClient.setRoot("Admin");
    }
}
