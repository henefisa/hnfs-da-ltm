package Models;

import javafx.beans.property.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

public class Exam implements Externalizable {
    private static final long serialVersionUID = 1L;
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty duration =new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> startTime = new SimpleObjectProperty<>();
    private final BooleanProperty checkTime =new SimpleBooleanProperty();

    public Exam() {

    }
    public Exam(String id,String name) {
        setId(id);
        setName(name);
    }
    public  Exam(String name, int duration, LocalDate startTime, boolean checkTime){
        setName(name);
        setDuration(duration);
        setStartTime(startTime);
        setCheckTime(checkTime);
    }
    public  Exam(String id,String name,int duration,LocalDate startTime, boolean checkTime){
        setId(id);
        setName(name);
        setDuration((duration));
        setStartTime(startTime);
        setCheckTime(checkTime);
    }

    public Exam(String name, int duration, LocalDate startTime) {
        setName(name);
        setDuration(duration);
        setStartTime(startTime);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getDuration() {
        return duration.get();
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public LocalDate getStartTime() {
        return startTime.get();
    }

    public ObjectProperty<LocalDate> startTimeProperty() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime.set(startTime);
    }

    public boolean isCheckTime() {
        return checkTime.get();
    }

    public BooleanProperty checkTimeProperty() {
        return checkTime;
    }

    public void setCheckTime(boolean checkTime) {
        this.checkTime.set(checkTime);
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
        out.writeObject(getDuration());
        out.writeObject(getStartTime());
        out.writeObject(isCheckTime());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId((String) in.readObject());
        setName((String) in.readObject());
        setDuration((Integer) in.readObject());
        setStartTime((LocalDate) in.readObject());
        setCheckTime((Boolean) in.readObject());
    }
}
