package Server;

import API.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            IUser rUser = new UserImpl();
            LocateRegistry.createRegistry(9090);
            //đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:9090/user", rUser);
            System.out.println("Server started");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
