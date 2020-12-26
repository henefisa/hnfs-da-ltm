package API;

import Models.Exam;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IExam extends Remote {
    public boolean createExam(Exam exam) throws RemoteException;

    public List<Exam> getExams() throws RemoteException;
}
