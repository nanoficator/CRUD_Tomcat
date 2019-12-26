package service;

import DAO.UserServiceDao;
import DAO.UserServiceDaoFactory;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() throws DBException {
        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            return dao.getAllData();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteAllUsers() throws DBException {
        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            dao.deleteAllData();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean isExistUserName(String userName) throws DBException {
        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try{
            User userFromDB = dao.getDataByUserName(userName);
            if (userFromDB != null) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserByID(Long id) throws DBException {
        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            return dao.getDataByID(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserByUserName(String userName) throws DBException {
        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            return dao.getDataByUserName(userName);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public String addUser(User user) throws DBException {

        if (isExistUserName(user.getUserName())) {
            return "Error: Username exists!";
        }

        if (user.getAge() < 0) {
            return "Error: Age can not be negative!";
        }

        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            dao.addData(user);
            return "User was added!";
        } catch (SQLException e) {
            throw new DBException(e);
        }
            }

    public String deleteUser(User user) throws DBException {

        User userFromDB = getUserByUserName(user.getUserName());

        if (userFromDB == null) {
            return "Error: User does not exist!";
        }

        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            dao.deleteData(userFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public String deleteUserById(Long id) throws DBException {

        User userFromDB = getUserByID(id);

        if (userFromDB == null) {
            return "Error: User does not exist!";
        }

        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            dao.deleteData(userFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public String changeUser(User changedUser) throws DBException {

        Long id = changedUser.getId();
        String newFirstName = changedUser.getFirstName();
        String newSecondName = changedUser.getSecondName();
        String newUserName = changedUser.getUserName();
        String newPassword = changedUser.getPassword();
        Long newAge = changedUser.getAge();
        String newGender = changedUser.getGender();
        String newRole = changedUser.getRole();

        User userFromDBById = getUserByID(id);
        User userFromDBByUserName = getUserByUserName(newUserName);

        if (userFromDBById == null) {
            return "Error: User does not exist!";
        }

        if (id == null ||
                newFirstName.equals("") ||
                newSecondName.equals("") ||
                newUserName.equals("") ||
                newPassword.equals("") ||
                newAge == null ||
                newGender.equals("") ||
                newRole.equals("")) {
            return "Error: All fields are required!";
        }

        if (userFromDBByUserName != null && !userFromDBByUserName.getId().equals(id)) {
            return "Error: Username exists!";
        }

        if (newAge < 0) {
            return "Error: Age can not be negative!";
        }

        UserServiceDao dao = UserServiceDaoFactory.getDao();
        try {
            dao.changeFirstName(id, newFirstName);
            dao.changeSecondName(id, newSecondName);
            dao.changeUserName(id, newUserName);
            dao.changePassword(id, newPassword);
            dao.changeAge(id, newAge);
            dao.changeGender(id, newGender);
            dao.changeRole(id, newRole);
            return "Changes saved!";
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
