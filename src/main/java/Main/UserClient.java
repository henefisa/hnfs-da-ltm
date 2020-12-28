package Main;

import API.IExam;
import API.IExamServer;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserClient extends Application {
    private static User user;
    private static Scene scene;
    private static String exam_id;
    private static String examiner_id;

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserClient.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        UserClient.scene = scene;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
    }

    public static String getExam_id() {
        return exam_id;
    }

    public static void setExam_id(String exam_id) {
        UserClient.exam_id = exam_id;
    }

    public static String getExaminer_id() {
        return examiner_id;
    }

    public static void setExaminer_id(String examiner_id) {
        UserClient.examiner_id = examiner_id;
    }

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Trang chủ");
//        stage.setOnCloseRequest(e -> {
//            try {
//                e.consume();
//                IExamServer iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
//                iExamServer.unregister();
//                stage.close();
//            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
//                ex.printStackTrace();
//            }
//        });
        stage.show();
    }

}
