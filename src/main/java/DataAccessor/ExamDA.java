package DataAccessor;

import Models.Exam;
import Utils.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamDA {
    public boolean createExam(Exam exam) {
        String sql = "insert into Exam values(?,?,?,?,?)";
        boolean rowInserted = false;
        try {
            ConnectDB.connect();
            UUID uuid = UUID.randomUUID();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, uuid.toString());
            statement.setString(2, exam.getName());
            statement.setInt(3,exam.getDuration());
            statement.setDate(4, Date.valueOf(exam.getStartTime().toString()));
            statement.setBoolean(5,exam.isCheckTime());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        String sql = "select * from Exam";

        try {
            ConnectDB.connect();
            Statement statement = ConnectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int duration =resultSet.getInt("duration");
                Date startTime=resultSet.getDate("startTime");
                LocalDate date=startTime.toLocalDate();
                Boolean checkTime=resultSet.getBoolean("checkTime");
                Exam exam = new Exam(id, name,duration,date,checkTime);

                exams.add(exam);
            }

            resultSet.close();
            statement.close();

            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    public Exam getById(String e_id) {
        String sql = "select * from Exam where id = ?";
        Exam exam = null;
        try {
            ConnectDB.connect();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, e_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int duration =resultSet.getInt("duration");
                Date startTime=resultSet.getDate("startTime");
                LocalDate date=startTime.toLocalDate();
                Boolean checkTime=resultSet.getBoolean("checkTime");
                exam = new Exam(id, name,duration,date,checkTime);
            }

            resultSet.close();
            statement.close();

            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exam;
    }
}
