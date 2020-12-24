package API;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImpl extends UnicastRemoteObject implements ILogin {

    public LoginImpl() throws RemoteException {
    }

    @Override
    public boolean Login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return false;
    }
}
