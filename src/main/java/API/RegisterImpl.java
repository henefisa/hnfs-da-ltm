package API;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterImpl extends UnicastRemoteObject implements IRegister {
    public RegisterImpl() throws RemoteException {
    }

    @Override
    public String createUser(String username, String password) throws RemoteException {
        return "User created!";
    }
}
