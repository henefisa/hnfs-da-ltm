package API;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILogin extends Remote {
    public boolean Login(String username, String password) throws RemoteException;
}
