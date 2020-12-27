package Server;

import API.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IServer {

    protected Server() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            IUser rUser = new UserImpl();
            IExam rExam = new ExamImpl();
            LocateRegistry.createRegistry(9090);
            //đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:9090/user", rUser);
            Naming.bind("rmi://localhost:9090/exam", rExam);
            System.out.println("Server started");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return null;
    }

    @Override
    public void registerExaminer() throws RemoteException {

    }
}
