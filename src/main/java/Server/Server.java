package Server;

import API.ILogin;
import API.LoginImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            ILogin rLogin = new LoginImpl();
            LocateRegistry.createRegistry(9090);
            //đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:9090/login", rLogin);
            System.out.println("Server started");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
