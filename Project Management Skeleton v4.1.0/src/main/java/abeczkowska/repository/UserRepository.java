package abeczkowska.repository;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.User;
import abeczkowska.persistence.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements JDBCRepository {

    private static final Logger REPO_LOGGER = LoggerFactory.getLogger(UserRepository.class.getName());
    private final Connection connection;

    public UserRepository() {
        connection = DatabaseConn.getInstance().getConnection();
    }

    @Override
    public Persistable findById(int id) {

        User user = null;
        String sqlQuery = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                REPO_LOGGER.info("CREATED USER: \n" + user.toString());
            }

        }
        catch(SQLException e) {
            // here will be logging sl4fj
            REPO_LOGGER.error("A PROBLEM ENCOUNTERED WITH EXCEPTION: " + e.getMessage());
        }

        return user;
    }

    @Override
    public List<? extends Persistable> findAll() {

        String sqlQuery = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

            REPO_LOGGER.info("FOUND: " + users.size() + " users");

        }
        catch(SQLException e) {
            //  sl4fj
            REPO_LOGGER.error("A PROBLEM ENCOUNTERED WITH EXCEPTION: " + e.getMessage());
        }

        return users;
    }

    @Override
    public Persistable create(Persistable persistable) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ((User) persistable).getUsername());
            ps.setString(2, ((User) persistable).getPassword());

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY CREATED USER: " + persistable.toString());
        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT CREATE USER");
        }

        return persistable;
    }

    @Override
    public void update(Persistable persistable, int id) {
        String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ((User) persistable).getUsername());
            ps.setString(2, ((User) persistable).getPassword());
            ps.setInt(3, id);

            if(ps.executeUpdate() == 1) {
                REPO_LOGGER.info("SUCCESSFULLY UPDATED USER: " + persistable.toString());
            }

        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT UPDATE USER");
        }
    }

    @Override
    public void delete(Persistable persistable) {
        int id = ((User) persistable).getId();

        String query = "DELETE FROM users WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED USER: " + persistable.toString());
        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT DELETE USER");
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED USER with ID: " + id);
        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT DELETE USER");
        }
    }
}
