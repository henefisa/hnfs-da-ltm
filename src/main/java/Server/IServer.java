package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    public String sayHello(String name) throws RemoteException;
    public void registerExaminer(String examName, Examiner examiner) throws RemoteException;
}
