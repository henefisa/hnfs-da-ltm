package Server;

import API.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server extends UnicastRemoteObject implements IServer {
    private HashMap<String, List<Examiner>> exams;

    protected Server() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            IUser rUser = new UserImpl();
            IExam rExam = new ExamImpl();
            IQuestion rQuestion=new QuestionImpl();
            LocateRegistry.createRegistry(9090);
            //đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:9090/user", rUser);
            Naming.bind("rmi://localhost:9090/exam", rExam);
            Naming.bind("rmi://localhost:9090/questions",rQuestion);
            System.out.println("Server started");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return null;
    }

    @Override
    public void registerExaminer(String examName, Examiner examiner) throws RemoteException {
        List<Examiner> exam = exams.get(examName);
        if (exam != null) {
            exam.add(examiner);
        } else {
            exam = new ArrayList<>();
            exam.add(examiner);

        }
        exams.put(examName, exam);
    }
}
