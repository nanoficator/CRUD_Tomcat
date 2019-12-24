package DAO;

import exception.DBException;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserServiceDaoFactory {

    public static UserServiceDao getDao() throws DBException {
        try {

            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String daoType = properties.getProperty("DAOType");

            if (daoType.toLowerCase().equals("hql")) {
                return new UserServiceHqlDao(DBHelper.getInstance().getConfiguration());
            } else if (daoType.toLowerCase().equals("sql")) {
                return new UserServiceSqlDao(DBHelper.getInstance().getConnection());
            }

            return null;

        } catch (IOException e) {

            e.printStackTrace();
            throw new DBException(e);

        }
    }

}