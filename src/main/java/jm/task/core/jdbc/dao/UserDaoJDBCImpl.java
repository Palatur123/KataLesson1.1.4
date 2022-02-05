package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydb.users" +
                    "(id mediumint auto_increment," +
                    " name VARCHAR(45), " +
                    "lastname VARCHAR(45), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))");
            System.out.println("Таблица создалась");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop table if exists mydb.users");
            System.out.println("Таблица удалилась");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String mysql = "INSERT INTO mydb.users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(mysql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("Новый юзер добавлен в базу данных" + name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String mysql = "DELETE FROM mydb.users where id";
            statement.executeUpdate(mysql);
            System.out.println("Юзер удален");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        String mysql = "SELECT id, name, lastName, age from mydb.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(mysql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;
    }

    public void cleanUsersTable() {
        String mysql = "DELETE FROM mydb.users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(mysql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось очистить");
        }

    }
}
