package DataAccessor;

import Models.Questions;
import Utils.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionsDA {
    public boolean createQuestion(Questions questions) {
        String sql = "insert into Questions values(?, ?, ?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            ConnectDB.connect();
            UUID uuid = UUID.randomUUID();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, uuid.toString());
            statement.setString(2, questions.getQuestion());
            statement.setString(3, questions.getAnswer1());
            statement.setString(4, questions.getAnswer2());
            statement.setString(5, questions.getAnswer3());
            statement.setString(6, questions.getAnswer4());
            statement.setString(7, questions.getAnswer_true());
            statement.setString(8, questions.getExam_id());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public List<Questions> getQuestions() {
        List<Questions> questions = new ArrayList<>();
        String sql = "select * from Questions";

        try {
            ConnectDB.connect();
            Statement statement = ConnectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String question = resultSet.getString("question");
                String answer1 = resultSet.getString("answer1");
                String answer2 = resultSet.getString("answer2");
                String answer3 = resultSet.getString("answer3");
                String answer4 = resultSet.getString("answer4");
                String answer_true = resultSet.getString("answer_true");
                String exam_id = resultSet.getString("exam_id");


                Questions questions_item = new Questions(id, question, answer1, answer2, answer3, answer4, answer_true, exam_id);
                questions.add(questions_item);
            }

            resultSet.close();
            statement.close();

            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public Questions getQuestion(String question_id) {
        String sql = "select * from Questions where id = " + question_id;
        Questions questions = null;
        try {
            ConnectDB.connect();
            Statement statement = ConnectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String question = resultSet.getString("question");
                String answer1 = resultSet.getString("answer1");
                String answer2 = resultSet.getString("answer2");
                String answer3 = resultSet.getString("answer3");
                String answer4 = resultSet.getString("answer4");
                String answer_true = resultSet.getString("answer_true");
                String exam_id = resultSet.getString("exam_id");
                questions = new Questions(id, question, answer1, answer2, answer3, answer4, answer_true, exam_id);

            }
            resultSet.close();
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<Questions> getQuestionByExamId(String i_exam_id) {
        String sql = "select * from Questions where exam_id = " + i_exam_id;
        List<Questions> questions = new ArrayList<>();
        try {
            ConnectDB.connect();
            Statement statement = ConnectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String question = resultSet.getString("question");
                String answer1 = resultSet.getString("answer1");
                String answer2 = resultSet.getString("answer2");
                String answer3 = resultSet.getString("answer3");
                String answer4 = resultSet.getString("answer4");
                String answer_true = resultSet.getString("answer_true");
                String exam_id = resultSet.getString("exam_id");
                Questions questions_item = new Questions(id, question, answer1, answer2, answer3, answer4, answer_true, exam_id);
                questions.add(questions_item);
            }

            resultSet.close();
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
