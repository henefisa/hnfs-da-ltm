package API;

import Models.Exam;
import Models.Questions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IQuestion extends Remote {
    public boolean createQuestion(Questions questions) throws RemoteException;
}
