package API;

import DataAccessor.QuestionsDA;
import Models.Questions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuestionImpl extends UnicastRemoteObject implements IQuestion {
    private final QuestionsDA questionsDA;

    public QuestionImpl() throws RemoteException {
        this.questionsDA = new QuestionsDA();
    }

    @Override
    public boolean createQuestion(Questions questions) throws RemoteException {
        return questionsDA.createQuestion(questions);
    }

    @Override
    public List<Questions> getQuestion() throws RemoteException {
        return questionsDA.getQuestions();
    }
}
