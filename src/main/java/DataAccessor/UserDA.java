package DataAccessor;

import Models.User;
import Utils.ConnectDB;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class UserDA {

    private boolean checkUserExist(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        boolean exist = false;
        try {
            ConnectDB.connect();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exist = true;
            }
            ConnectDB.disconnect();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public boolean createUser(User user) {
        String sql = "Insert into Users(id, username, fullName, birthday, password) values (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        if (checkUserExist(user.getUsername()))
            return false;

        try {
            ConnectDB.connect();
            UUID uuid = UUID.randomUUID();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            statement.setString(1, uuid.toString());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getFullName());
            statement.setDate(4, Date.valueOf(user.getBirthday().toString()));
            statement.setString(5, hashedPassword);
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            ConnectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public User loginUser(String uUsername, String uPassword) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        User user = null;
        try {
            ConnectDB.connect();
            PreparedStatement statement = ConnectDB.getConnection().prepareStatement(sql);
            statement.setString(1, uUsername);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                Date birthday = resultSet.getDate("birthday");
                LocalDate date = birthday.toLocalDate();
                user = new User(id, username, fullName, date, password);
            }
            ConnectDB.disconnect();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            System.out.println(true);
            BCrypt.Result result = BCrypt.verifyer().verify(uPassword.toCharArray(), user.getPassword());
            if (result.verified) {
                return user;
            }
        }
        return null;
    }
}

