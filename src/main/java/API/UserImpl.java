package API;

import DataAccessor.UserDA;
import Models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserImpl extends UnicastRemoteObject implements IUser {
    private final UserDA userDA;

    public UserImpl() throws RemoteException {
        this.userDA = new UserDA();
    }

    @Override
    public boolean register(User user) {
        return this.userDA.createUser(user);
    }

    @Override
    public User login(String username, String password){
        User user = userDA.loginUser(username, password);
        if (user != null) System.out.println("Login success!");
        return user;

    }

    @Override
    public boolean get(String id) throws RemoteException {
        return false;
    }
}
