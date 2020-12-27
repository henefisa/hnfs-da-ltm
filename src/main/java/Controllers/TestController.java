package Controllers;

import API.IExam;
import API.IExamServer;
import API.IQuestion;
import Main.UserClient;
import Models.Exam;
import Models.Questions;
import Models.User;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestController {
    private int interval = 10;
    private int currentQuestion = 0;
    private int count = 0;
    @FXML
    private Label an_1;
    @FXML
    private Label an_2;
    @FXML
    private Label an_3;
    @FXML
    private Label an_4;
    @FXML
    private Label userName;
    @FXML
    private Label maUser;

    @FXML
    private Label time;

    @FXML
    private RadioButton ans_1;
    @FXML
    private RadioButton ans_2;
    @FXML
    private RadioButton ans_3;
    @FXML
    private RadioButton ans_4;
    @FXML
    private Label cauhoi;
    @FXML
    private Label soCau;
    @FXML
    private Label diem;
    public   static  String kq;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private Timer timer;
    private Timer questionTimer;
    private List<Questions> questionsList = new ArrayList<>();
    private int mark = 0;

    public void initialize() {
        ans_1.setToggleGroup(toggleGroup);
        ans_1.setUserData("a");
        ans_2.setToggleGroup(toggleGroup);
        ans_2.setUserData("b");
        ans_3.setToggleGroup(toggleGroup);
        ans_3.setUserData("c");
        ans_4.setToggleGroup(toggleGroup);
        ans_4.setUserData("d");

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (toggleGroup.getSelectedToggle() != null) {
                    String ans = toggleGroup.getSelectedToggle().getUserData().toString();
                    if (ans.equals(questionsList.get(currentQuestion - 1).getAnswer_true())) {
                        mark += 10;
                        diem.setText(String.valueOf(mark));
                        System.out.println(ans);
                    }
                    count = count + (10 - (count % 10));

                }
            }
        });

        User user = UserClient.getUser();
        if (user != null) {
            userName.setText(user.getUsername());
            maUser.setText(user.getFullName());

        }
        try {
            IQuestion iQuestion = (IQuestion) Naming.lookup("rmi://localhost:9090/questions");
            questionsList = iQuestion.getQuestionByExamID(UserClient.getExam_id());
            renderAnswer();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void leaveTest() {
        timer.cancel();
        questionTimer.cancel();
        try {
            IExamServer iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
            iExamServer.unregister();
            try {
                UserClient.setRoot("finish");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAnswer() {
        if(currentQuestion < questionsList.size()){
            interval = 10;
            Questions questions = questionsList.get(currentQuestion++);
            cauhoi.setText(questions.getQuestion());
            soCau.setText(currentQuestion + "");
            an_1.setText(questions.getAnswer1());
            an_2.setText(questions.getAnswer2());
            an_3.setText(questions.getAnswer3());
            an_4.setText(questions.getAnswer4());
            toggleGroup.selectToggle(null);
        }
    }

    public void renderAnswer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (interval > 0) {
                    Platform.runLater(() -> {
                        time.setText("" + interval);
                    });
                    interval--;
                } else {
                    timer.cancel();
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        questionTimer = new Timer();
        questionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (count >= questionsList.size() * 10) {
                    questionTimer.cancel();
                    timer.cancel();
                    kq=diem.getText();
                    try {
                        UserClient.setRoot("finish");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (count % 10 == 0) {
                    Platform.runLater(() -> {
                        updateAnswer();
                        System.out.println("count" + count);
                    });
                    count++;
                } else {
                    count++;
                }
            }
        }, 0, 1000);
    }
}
