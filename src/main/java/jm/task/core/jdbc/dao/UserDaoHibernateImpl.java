package jm.task.core.jdbc.dao;

import com.mysql.cj.Query;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();
    private SessionFactory sessionFactory = util.getSessionFactory();
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(idUsers INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nameUsers VARCHAR(255), lastNameUsers VARCHAR(255), ageUsers INT(3))").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession();) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            list = session.createQuery("From User", User.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
                throw new RuntimeException();
        } return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
