package DAO;

import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServiceSqlDao implements UserServiceDao {

    private Connection connection;

    public UserServiceSqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllData() throws SQLException {
        List<User> allData = new LinkedList<>();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            Long id = resultSet.getLong(1);
            String firstName = resultSet.getString(3);
            String secindName = resultSet.getString(6);
            String userName = resultSet.getString(7);
            String password = resultSet.getString(5);
            Long age = resultSet.getLong(2);
            String gender = resultSet.getString(4);
            allData.add(new User(id, firstName, secindName, userName, password, age, gender));
        }
        resultSet.close();
        statement.close();
        return allData;
    }

    @Override
    public void deleteAllData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM users");
        statement.close();
    }

    @Override
    public void addData(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (first_name, second_name, user_name, password, age, gender) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setLong(5, user.getAge());
        preparedStatement.setString(6, user.getGender());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteData(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public User getDataByID(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        if (resultSet.next()) {
            User userById = new User();
            userById.setId(resultSet.getLong(1));
            userById.setFirstName(resultSet.getString(3));
            userById.setSecondName(resultSet.getString(6));
            userById.setUserName(resultSet.getString(7));
            userById.setPassword(resultSet.getString(5));
            userById.setAge(resultSet.getLong(2));
            userById.setGender(resultSet.getString(4));
            return userById;
        }
        return null;
    }

    @Override
    public User getDataByUserName (String userName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
        preparedStatement.setString(1,userName);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        if (resultSet.next()) {
            User userByUserName = new User();
            userByUserName.setId(resultSet.getLong(1));
            userByUserName.setFirstName(resultSet.getString(3));
            userByUserName.setSecondName(resultSet.getString(6));
            userByUserName.setUserName(resultSet.getString(7));
            userByUserName.setPassword(resultSet.getString(5));
            userByUserName.setAge(resultSet.getLong(2));
            userByUserName.setGender(resultSet.getString(4));
            return userByUserName;
        }
        return null;
    }

    @Override
    public void changeFirstName(Long id, String newFirstName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET first_name = ? WHERE id = ?");
        preparedStatement.setString(1, newFirstName);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void changeSecondName(Long id, String newSecondName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET second_name = ? WHERE id = ?");
        preparedStatement.setString(1, newSecondName);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void changeUserName(Long id, String newUserName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET user_name = ? WHERE id = ?");
        preparedStatement.setString(1, newUserName);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void changePassword(Long id, String newPassword) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
        preparedStatement.setString(1, newPassword);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void changeAge(Long id, Long newAge) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET age = ? WHERE id = ?");
        preparedStatement.setLong(1, newAge);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void changeGender(Long id, String newGender) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET gender = ? WHERE id = ?");
        preparedStatement.setString(1, newGender);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

}