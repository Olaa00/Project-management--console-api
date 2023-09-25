package abeczkowska.repository;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.Role;
import abeczkowska.model.User;
import pl.ttpsc.javaupdate.project.service.LoginREC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import abeczkowska.persistence.Persistable;


public class UserAuthorizationRepository implements JDBCRepository{
    private final Connection connection;

    public UserAuthorizationRepository() {
        connection = DatabaseConn.getInstance().getConnection();
    }

     //authorization //todo
    public Optional<User> authorize(LoginREC loginREC) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Ustawia parametry zapytania na podstawie przekazanego obiektu LoginREC.
            ps.setString(1, loginREC.username());
            ps.setString(2, loginREC.password());

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    //nowy obiekt User
                    User entity = new User();
                    entity.setId(rs.getInt("id"));
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setGlobalRole(Role.valueOf(rs.getString("global_role")));

                    return Optional.of(entity); // Zwraca opcjonalną instancję obiektu User, jeśli znaleziono użytkownika.
                }
            }
        }
        catch(SQLException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public Persistable findById(int id) {
        return null;
    }

    @Override
    public List<? extends Persistable> findAll() {
        return null;
    }

    @Override
    public Persistable create(Persistable persistable) {
        return null;
    }

    @Override
    public void update(Persistable persistable, int id) {

    }

    @Override
    public void delete(Persistable persistable) {

    }

    @Override
    public void deleteById(int id) {

    }
}

