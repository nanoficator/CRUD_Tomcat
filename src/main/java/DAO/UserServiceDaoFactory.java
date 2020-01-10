package DAO;

import exception.DBException;
import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserServiceDaoFactory {

    public static UserServiceDao getDao() throws DBException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String daoType = properties.getProperty("DAOType");

            if (daoType.equalsIgnoreCase("hql")) {
                return new UserServiceHqlDao(DBHelper.getInstance().getConfiguration());
            } else if (daoType.equalsIgnoreCase("sql")) {
                return new UserServiceSqlDao(DBHelper.getInstance().getConnection());
            }

            return null;

        } catch (IOException e) {

            e.printStackTrace();
            throw new DBException(e);

        }
    }
}