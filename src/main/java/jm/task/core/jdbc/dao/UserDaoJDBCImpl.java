package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
    String input = """
            CREATE TABLE IF NOT EXISTS users.user (
                        id bigserial primary key,
                        name varchar(50),
                        last_name varchar(50),
                        age smallint
                        );
            """;
        try (Connection connection = getConnection()) {

                Statement statement = connection.createStatement();
                statement.execute(input);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String input = """
            DROP TABLE IF EXISTS users.user;
            """;
        try (Connection connection = getConnection()) {

                Statement statement = connection.createStatement();
                statement.execute(input);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String input = """
            INSERT INTO users.user (name, last_name, age) VALUES (?,?,?)
            """;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(input);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String input = """
             DELETE FROM users.user WHERE id = ?;
            """;
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(input);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String input = """
            SELECT * FROM users.user;
            """;
        try (Connection connection = getConnection()) {
            ResultSet resultSet = connection.prepareStatement(input).executeQuery();
            while (resultSet.next()){
                userList.add(new User(
                        (resultSet.getLong("id")),
                        (resultSet.getString("name")),
                        (resultSet.getString("last_name")),
                        (resultSet.getByte("age"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String input = """
             DELETE FROM users.user;
            """;
        try (Connection connection = getConnection()) {
                Statement statement = connection.createStatement();
                statement.execute(input);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
