package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;

public class UserServiceHqlDao implements UserServiceDao {

    private static Configuration configuration;

    public UserServiceHqlDao(Configuration configuration) {
        this.configuration = configuration;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllData() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public void deleteAllData() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void addData(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteData(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getDataByID(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        User userFromDB = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return userFromDB;
    }

    @Override
    public User getDataByUserName(String userName) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE user_name = :user_name");
        query.setParameter("user_name", userName);
        User userFromDB = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return userFromDB;
    }

    @Override
    public void changeFirstName(Long id, String newFirstName) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET firstName = :firstName WHERE id = :id");
        query.setParameter("firstName", newFirstName);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeSecondName(Long id, String newSecondName) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET secondName = :secondName WHERE id = :id");
        query.setParameter("secondName", newSecondName);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserName(Long id, String newUserName) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET userName = :newUserName WHERE id = :id");
        query.setParameter("newUserName", newUserName);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET password = :password WHERE id = :id");
        query.setParameter("password", newPassword);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeAge(Long id, Long newAge) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET age = :age WHERE id = :id");
        query.setParameter("age", newAge);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeGender(Long id, String newGender) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET gender = :gender WHERE id = :id");
        query.setParameter("gender", newGender);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeRole(Long id, String newRole) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET role = :role WHERE id = :id");
        query.setParameter("role", newRole);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}