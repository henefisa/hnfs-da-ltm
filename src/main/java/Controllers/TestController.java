package Controllers;

import API.IExamServer;
import javafx.fxml.FXML;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestController {
    @FXML
    private void leaveTest() {
        try {
            IExamServer iExamServer = (IExamServer) Naming.lookup("rmi://localhost:9090/examServer");
            iExamServer.unregister();
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
