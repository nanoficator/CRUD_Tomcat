package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceDao {

    List<User> getAllData() throws SQLException;

    void deleteAllData() throws SQLException;

    void addData(User user) throws SQLException;

    void deleteData(User user) throws SQLException;

    User getDataByID(Long id) throws SQLException;

    User getDataByUserName(String userName) throws SQLException;

    void changeFirstName(Long id, String newFirstName) throws SQLException;

    void changeSecondName(Long id, String newSecondName) throws SQLException;

    void changeUserName(Long id, String newUserName) throws SQLException;

    void changePassword(Long id, String newPassword) throws SQLException;

    void changeAge(Long id, Long newAge) throws SQLException;

    void changeGender(Long id, String newGender) throws SQLException;

}