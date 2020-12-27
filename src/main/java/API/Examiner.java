package API;

public class Examiner {
    private String id;
    private IExam iExam;

    public Examiner(String id, IExam iExam) {
        this.id = id;
        this.iExam = iExam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IExam getiExam() {
        return iExam;
    }

    public void setiExam(IExam iExam) {
        this.iExam = iExam;
    }
}
