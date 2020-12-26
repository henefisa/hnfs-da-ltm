package Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

public class User implements Externalizable {
    private static final long serialVersionUID = 1L;
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty fullName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private final StringProperty password = new SimpleStringProperty();

    public User(){}

    public User(String username, String fullName, LocalDate birthday, String password) {
        setUsername(username);
        setFullName(fullName);
        setPassword(password);
        setBirthday(birthday);
    }

    public User(String id, String username, String fullName, LocalDate birthday, String password) {
        setId(id);
        setUsername(username);
        setFullName(fullName);
        setPassword(password);
        setBirthday(birthday);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getId());
        out.writeObject(getUsername());
        out.writeObject(getFullName());
        out.writeObject(getBirthday());
        out.writeObject(getPassword());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId((String) in.readObject());
        setUsername((String) in.readObject());
        setFullName((String) in.readObject());
        setBirthday((LocalDate) in.readObject());
        setPassword((String) in.readObject());

    }
}
