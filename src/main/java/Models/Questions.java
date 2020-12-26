package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Questions implements Externalizable {
    private static final long serialVersionUID = 1L;
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty question = new SimpleStringProperty();
    private final StringProperty answer1 = new SimpleStringProperty();
    private final StringProperty answer2 = new SimpleStringProperty();
    private final StringProperty answer3 = new SimpleStringProperty();
    private final StringProperty answer4 = new SimpleStringProperty();
    private final StringProperty answer_true = new SimpleStringProperty();
    private final StringProperty exam_id = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public StringProperty questionProperty() {
        return question;
    }

    public String getAnswer1() {
        return answer1.get();
    }

    public void setAnswer1(String answer1) {
        this.answer1.set(answer1);
    }

    public StringProperty answer1Property() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2.get();
    }

    public void setAnswer2(String answer2) {
        this.answer2.set(answer2);
    }

    public StringProperty answer2Property() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3.get();
    }

    public void setAnswer3(String answer3) {
        this.answer3.set(answer3);
    }

    public StringProperty answer3Property() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4.get();
    }

    public void setAnswer4(String answer4) {
        this.answer4.set(answer4);
    }

    public StringProperty answer4Property() {
        return answer4;
    }

    public String getAnswer_true() {
        return answer_true.get();
    }

    public void setAnswer_true(String answer_true) {
        this.answer_true.set(answer_true);
    }

    public StringProperty answer_trueProperty() {
        return answer_true;
    }

    public String getExam_id() {
        return exam_id.get();
    }

    public void setExam_id(String exam_id) {
        this.exam_id.set(exam_id);
    }

    public StringProperty exam_idProperty() {
        return exam_id;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getId());
        out.writeObject(getQuestion());
        out.writeObject(getAnswer1());
        out.writeObject(getAnswer2());
        out.writeObject(getAnswer3());
        out.writeObject(getAnswer4());
        out.writeObject(getAnswer_true());
        out.writeObject(getExam_id());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId((String) in.readObject());
        setQuestion((String) in.readObject());
        setAnswer1((String) in.readObject());
        setAnswer2((String) in.readObject());
        setAnswer3((String) in.readObject());
        setAnswer4((String) in.readObject());
        setAnswer_true((String) in.readObject());
        setExam_id((String) in.readObject());
    }


}
