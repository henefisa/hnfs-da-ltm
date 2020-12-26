package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Exam implements Externalizable {
    private static final long serialVersionUID = 1L;
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();

    public Exam() {
    }

    public Exam(String name) {
        setName(name);
    }

    public Exam(String id, String name) {
        setId(id);
        setName(name);
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getId());
        out.writeObject(getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId((String) in.readObject());
        setName((String) in.readObject());
    }
}
