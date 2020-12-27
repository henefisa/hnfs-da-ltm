package API;

import Main.UserClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ExamServerImpl extends UnicastRemoteObject implements IExamServer {
    public static HashMap<String, List<Examiner>> exams;

    public ExamServerImpl() throws RemoteException {
        exams = new HashMap<>();
    }

    @Override
    public void register(String id, IExam iExam) throws RemoteException {
        if (exams.get(id) == null) {
            exams.put(id, new ArrayList<>());
        }
        UUID uuid = UUID.randomUUID();
        Examiner examiner = new Examiner(uuid.toString(), iExam);
        UserClient.setExaminer_id(uuid.toString());
        UserClient.setExam_id(id);
        exams.get(id).add(examiner);
        exams.get(id).forEach(e -> {
            try {
                e.getiExam().printMessage(String.valueOf(exams.get(id).size()));
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    @Override
    public void unregister() throws RemoteException {
        List<Examiner> exam = exams.get(UserClient.getExam_id());
        if (exam != null) {
            for (int i = 0; i < exam.size(); i++) {
                boolean same = exam.get(i).getId().equals(UserClient.getExaminer_id());
                System.out.println(UserClient.getExaminer_id());
                if (same) {
                    exam.remove(i);
                    System.out.println("Some user disconnected!");
                }
            }
        }

    }
}
