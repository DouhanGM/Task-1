package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String input = """
            CREATE TABLE IF NOT EXISTS users.user (
                        id bigserial primary key,
                        name varchar(50),
                        last_name varchar(50),
                        age smallint
                        );
            """;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(input).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String input = """
            DROP TABLE IF EXISTS users.user;
            """;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(input).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        String input = """
            SELECT * FROM users.user;
            """;
        List userList = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        userList = session.createSQLQuery(input).addEntity(User.class).getResultList();
        session.getTransaction().commit();
        return Collections.unmodifiableList((List<User>) userList);
    }

    @Override
    public void cleanUsersTable() {
        String input = """
             DELETE FROM users.user;
            """;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(input).executeUpdate();
        session.getTransaction().commit();
    }
}
