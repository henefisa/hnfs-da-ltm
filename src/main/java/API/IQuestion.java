package API;

import Models.Exam;
import Models.Questions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IQuestion extends Remote {
    boolean createQuestion(Questions questions) throws RemoteException;
    List<Questions> getQuestions() throws RemoteException;
    Questions getQuestion(String id) throws RemoteException;
    List<Questions> getQuestionByExamID(String i_exam_id) throws RemoteException;
}
