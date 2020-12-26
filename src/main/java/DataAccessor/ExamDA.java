package DataAccessor;

import Models.Exam;
import Utils.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamDA {
    private boolean createExam(Exam exam) {
        String sql = "insert into Exam values(?, ?)";
        boolean rowInserted = false;
        try {
            ConnectDB.connect();
            UUID uuid = UUID.randomUUID();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, uuid.toString());
            statement.setString(2, exam.getName());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    private List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        String sql = "select * from Exam";

        try {
            ConnectDB.connect();
            Statement statement = ConnectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                Exam exam = new Exam(id, name);
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
}
