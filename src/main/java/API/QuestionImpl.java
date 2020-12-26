package API;

import Models.Questions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuestionImpl extends UnicastRemoteObject implements IQuestion {

    protected QuestionImpl() throws RemoteException {
    }

    @Override
    public boolean createQuestion(Questions questions) throws RemoteException {
        return false;
    }
}
