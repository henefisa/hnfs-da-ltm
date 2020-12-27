package API;

import API.IExam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IExamServer extends Remote {
//    public void sayHello(String name) throws RemoteException;
//    public void registerExaminer(String name, Examiner examiner) throws RemoteException;
    public void register(String id, IExam iExam) throws RemoteException;
}
