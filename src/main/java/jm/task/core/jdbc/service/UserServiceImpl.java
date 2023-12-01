package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Create");
    }

    public void dropUsersTable() {
userDaoJDBC.dropUsersTable();
        System.out.println("Drop");
    }

    public void saveUser(String name, String lastName, byte age) {
userDaoJDBC.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных \n", name);
    }

    public void removeUserById(long id) {
userDaoJDBC.removeUserById(id);
        System.out.printf("remove %s \n", id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDaoJDBC.getAllUsers());
    }

    public void cleanUsersTable() {
userDaoJDBC.cleanUsersTable();
        System.out.println("Clean");
    }
}
