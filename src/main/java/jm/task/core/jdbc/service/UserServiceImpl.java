package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Create");
    }

    public void dropUsersTable() {
userDao.dropUsersTable();
        System.out.println("Drop");
    }

    public void saveUser(String name, String lastName, byte age) {
userDao.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных \n", name);
    }

    public void removeUserById(long id) {
userDao.removeUserById(id);
        System.out.printf("remove %s \n", id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDao.getAllUsers());
    }

    public void cleanUsersTable() {
userDao.cleanUsersTable();
        System.out.println("Clean");
    }
}
