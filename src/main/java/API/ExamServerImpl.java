package API;

import API.IExam;
import API.IExamServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamServerImpl extends UnicastRemoteObject implements IExamServer {
    private HashMap<String, List<IExam>> exams;

    public ExamServerImpl() throws RemoteException {
        this.exams = new HashMap<>();
    }

//    @Override
//    public void registerExaminer(String examName, Examiner examiner) throws RemoteException {
////        List<Examiner> exam = exams.get(examName);
//        if (exam == null) {
//            exam = new ArrayList<>();
//        }
//        exam.add(examiner);
//        exams.put(examName, exam);
//    }

//    @Override
//    public void sayHello(String id) throws RemoteException {
//
//    }

    @Override
    public void register(String id, IExam iExam) throws RemoteException {
        List<IExam> exam = exams.get(id);
        if (exam == null) {
            exam = new ArrayList<>();
        }
        exam.add(iExam);
        exams.put(id, exam);
        iExam.printMessage("123");
    }
}
