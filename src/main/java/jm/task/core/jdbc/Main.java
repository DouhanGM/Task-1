package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Alex", "mor", (byte) 30);
        userService.saveUser("Ralf", "far", (byte) 10);
        userService.saveUser("Pox", "half", (byte) 15);
        userService.saveUser("Faramir", "LastName", (byte) 100);
        for (User user:userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
