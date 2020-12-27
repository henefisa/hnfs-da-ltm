package API;

import Models.Questions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IQuestion extends Remote {
    public boolean createQuestion(Questions questions) throws RemoteException;
}
