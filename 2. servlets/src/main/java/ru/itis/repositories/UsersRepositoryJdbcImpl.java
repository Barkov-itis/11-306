package ru.itis.repositories;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private DataSource dataSource;

    private static final String SQL_INSERT_INTO_USERS = "insert into " +
            "driver(login,password,first_name,last_name, age) values ";

    private static final String SQL_FIND_ALL = "select * from driver";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void save(User entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = SQL_INSERT_INTO_USERS + "('" + entity.getLogin() +
                "', '" + entity.getPassword() +
                "', '" + entity.getFirstName() +
                "', '" + entity.getLastName() +
                "', '" + entity.getAge() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println(entity.getLogin() + " " + entity.getPassword() +
                " " + entity.getFirstName() + " " + entity.getLastName());
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .login(resultSet.getString("login"))
                    .build();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        return Optional.empty();
    }

    @Override
    public List findAllByAge() {
        return null;
    }
}
