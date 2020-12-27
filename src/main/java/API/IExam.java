package API;

import Models.Exam;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IExam extends Remote {
    boolean createExam(Exam exam) throws RemoteException;
    List<Exam> getExams() throws RemoteException;
    void printMessage(String message) throws RemoteException;

}
