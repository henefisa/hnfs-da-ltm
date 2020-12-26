package API;

import Models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {
    public boolean register(User user) throws RemoteException;
    public User login(String username, String password) throws RemoteException;
    public boolean get(String id) throws RemoteException;
}
