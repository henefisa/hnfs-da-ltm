package API;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRegister extends Remote {
    public String createUser(String username, String password) throws RemoteException;
}
