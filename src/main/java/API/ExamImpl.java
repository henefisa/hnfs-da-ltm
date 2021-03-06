package API;

import DataAccessor.ExamDA;
import Models.Exam;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ExamImpl extends UnicastRemoteObject implements IExam {
    private final ExamDA examDA;
    private String id;

    public ExamImpl() throws RemoteException {
        this.examDA = new ExamDA();

    }

    @Override
    public boolean createExam(Exam exam) {
        return examDA.createExam(exam);
    }

    @Override
    public List<Exam> getExams() throws RemoteException {
        return examDA.getExams();
    }

    @Override
    public void printMessage(String message) throws RemoteException {
        System.out.println(message);
    }


}
