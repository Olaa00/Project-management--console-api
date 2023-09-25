package abeczkowska.repository;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.Project;
import abeczkowska.model.ProjectBuilder;
import abeczkowska.model.Role;
import abeczkowska.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class ProjectsByUserRepository {
    private final Logger LOG = LoggerFactory.getLogger(ProjectsByUserRepository.class.getName());
    private final Connection connection;
    private final UserRepository userRepository;
    public ProjectsByUserRepository() {
        connection = DatabaseConn.getInstance().getConnection();
        userRepository = new UserRepository();
    }
    public Map<User, List<Role>> getProjectDetails(int projectId) {

        Map<User, List<Role>> userRoleMap = new HashMap<>();
// Wybiera kolumny user_id oraz user_project_role z tabeli user_projects i users.
//Łączy tabele user_projects  upr) i users  (u) na podstawie kolumny user_id (user_projects) oraz id (users).
//Pobiera rekordy tylko wtedy, gdy project_id w tabeli user_projects jest równe wartości parametru podanego w zapytaniu.
        String query = "SELECT upr.user_id, u.user_role " +
                "FROM user_project_role upr " +
                "INNER JOIN users u ON upr.user_id = u.id " +
                "WHERE upr.project_id = ?";


        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, projectId);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("user_id");
                Array rolesArr = resultSet.getArray("user_role");
                Role[] roles = (Role[]) rolesArr.getArray();
                List<Role> rolesList = Arrays.asList(roles);

                User entity = (User) userRepository.findById(id);

                userRoleMap.put(entity, rolesList);
            }

        }
        catch(SQLException e) {
             LOG.error("AN ERROR OCCURRED: " + e.getMessage());
        }

        return userRoleMap;
    }
    public List<Role> getUserRolesInProject(int userId, int projectId) {
        List<Role> roles = new ArrayList<>();

        String query = "SELECT user_role FROM user_projects WHERE user_id = ? AND project_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, projectId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(Role.valueOf(resultSet.getString("user_role")));
                }
            }
        }
        catch(SQLException sqlException) {
            LOG.error("FAILED TO GET THIS USER ROLES IN PROJECT");
        }

        return roles;
    }

    public List<Project> getAllUserProjects(int userId) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT p.* " +
                "FROM projects p " +
                "INNER JOIN user_project_roles up ON p.id = up.project_id " +
                "INNER JOIN users u ON up.user_id = u.id " +
                "WHERE u.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int projectId = resultSet.getInt("id");
                    String projectName = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    int creatorId = resultSet.getInt("user");

                    Project project = new ProjectBuilder()
                            .withId(projectId)
                            .withName(projectName)
                            .withDescription(description)
                            .withCreatorId(creatorId).build();

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
          LOG.error("FAILED TO GET ALL PROJECTS BY USER");
        }

        return projects;
    }

}


