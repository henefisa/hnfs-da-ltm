package Server;

import API.IUser;

public class Examiner {
    public String name;
    public IUser client;

    public Examiner(String name, IUser client){
        this.name = name;
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IUser getClient() {
        return client;
    }

    public void setClient(IUser client) {
        this.client = client;
    }
}
