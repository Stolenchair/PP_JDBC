package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {


        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(45) , lastname VARCHAR(45) , age int)");
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl createUsersTable");
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl dropUsersTable");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement ppstm = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
            ppstm.setString(1, name);
            ppstm.setString(2, lastName);
            ppstm.setInt(3, age);
            ppstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl saveUser");
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement ppstm = connection.prepareStatement("DELETE FROM users WHERE id =?")) {
            ppstm.setLong(1, id);
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl removeUserById");
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement ppstm = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = ppstm.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String lastName = resultSet.getString("LASTNAME");
                byte age = resultSet.getByte("AGE");

                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl getAllUsers");
        }

        return users;
    }

    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE users");
        } catch (SQLException e) {
            System.out.println("Проблема в UserDaoJDBCImpl cleanUsersTable");
        }

    }
}
