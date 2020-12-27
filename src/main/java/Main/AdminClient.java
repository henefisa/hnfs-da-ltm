package Main;

import Controllers.QuestionsController;
import Models.Exam;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminClient extends Application {
    private static Scene scene;
    Stage stage;
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminClient.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Admin"), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("admin");
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public void handleNew(ActionEvent actionEvent) {
    }


    public static void showUpdateExam(Exam exam) throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("addBaiThi");
        stage.setScene(new Scene(root, 640, 580));
        stage.setTitle("Quản lý câu hỏi thi");
        stage.initModality(Modality.APPLICATION_MODAL);
        QuestionsController controller=new QuestionsController();
        controller.createQuestion(exam);
        stage.show();
    }


}
