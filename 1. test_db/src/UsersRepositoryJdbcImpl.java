import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";

    public UsersRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);

            List<User> result = new ArrayList<>();

            while (resulSet.next()) {
                User user = new User(
                        resulSet.getLong(1),
                        resulSet.getString(2),
                        resulSet.getString("last_name"),
                        resulSet.getInt("age"));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<User> findAllByAge(Integer age) {
        //TODO: реализовать
        return null;
    }
}
