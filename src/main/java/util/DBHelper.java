package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper dbHelper;

    public static DBHelper getInstance() {
        if(dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/user_db?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "p@ssw0rd");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").          //db type
                    append("localhost:").             //host name
                    append("3306/").                  //port
                    append("user_db?").               //db name
                    append("user=root").              //login
                    append("&password=p@ssw0rd").     //password
                    append("&serverTimezone=UTC");    //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString(), "root", "p@ssw0rd");
            return connection;
        } catch (SQLException | ClassNotFoundException |IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}