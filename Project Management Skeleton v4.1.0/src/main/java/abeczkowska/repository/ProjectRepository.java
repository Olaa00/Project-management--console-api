package abeczkowska.repository;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.Project;
import abeczkowska.model.Role;
import abeczkowska.persistence.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements JDBCRepository{
    static final Logger REPO_LOGGER = LoggerFactory.getLogger(ProjectRepository.class.getName());
    private final Connection connection;

    public ProjectRepository() {
        connection = DatabaseConn.getInstance().getConnection();
    }

    public List<Project> findByName(String name) {

        List<Project> projects = new ArrayList<>();
        String sqlQuery = "SELECT * FROM projects WHERE name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setString(1, name);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                Project project = new Project();

                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatorId(resultSet.getInt("id"));

                projects.add(project);
            }
            REPO_LOGGER.info("FOUND : " + projects.size() + " NUMBER OF PROJECT with that name.");

        }
        catch(SQLException e) {
            //  sl4fj
            REPO_LOGGER.error("A PROBLEM ENCOUNTERED WITH EXCEPTION: " + e.getMessage());
        }

        return projects;
    }

    @Override
    public Persistable findById(int id) {

        Project project = null;
        String sqlQuery = "SELECT * FROM projects WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                project = new Project();

                project.setId(resultSet.getInt("project_id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatorId(resultSet.getInt("user"));

                REPO_LOGGER.info("FOUND PROJECT: \n" + project.toString());
            }

        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WITH EXCEPTION: " + e.getMessage());
        }

        return project;
    }

    @Override
    public List<? extends Persistable> findAll() {
        String sqlQuery = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Project project = new Project();

                project.setId(resultSet.getInt("project_id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatorId(resultSet.getInt("user"));

                projects.add(project);
            }

            REPO_LOGGER.info("NUMBER OF PROJECTS FOUND: " + projects.size() );

        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WITH EXCEPTION: " + e.getMessage());
        }

        return projects;
    }

    @Override
    public Persistable create(Persistable persistable) {
        return null;
    }

    public Persistable createProject(Persistable persistable, Role role) {
        String insertProjectSQL = "INSERT INTO projects (name, description, user_id) VALUES (?, ?, ?)";
        String insertProjectUserSQL = "INSERT INTO user_projects (project_id, user_id, user_role) VALUES (?, ?, ?)";

        int createdProjectId = 0;

        try {
            connection.setAutoCommit(false); // manual transaction management

            // add new project
            try (PreparedStatement insertProjectStatement = connection.prepareStatement(insertProjectSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                insertProjectStatement.setString(1, ((Project)persistable).getName());
                insertProjectStatement.setString(2, ((Project)persistable).getDescription());
                insertProjectStatement.setInt(3, ((Project)persistable).getCreatorId());
                insertProjectStatement.executeUpdate();

                // klucz główny (ID) dla kazdego nowego projektu
                try (ResultSet generatedKeys = insertProjectStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        createdProjectId = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("FAILED TO FIND GENERATED PROJECT ID. ");
                    }
                }
            }

            // przypisanie roli do usera, dodawanie do tabeli user_project_roles
            try (PreparedStatement insertProjectUserStatement = connection.prepareStatement(insertProjectUserSQL)) {

                insertProjectUserStatement.setInt(1, createdProjectId);
                insertProjectUserStatement.setInt(2, ((Project) persistable).getCreatorId());
                insertProjectUserStatement.setString(3, role.toString());
                insertProjectUserStatement.addBatch();

                insertProjectUserStatement.executeBatch(); //nowy rekord w DB
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return persistable;
    }

    public void addUserToProject( int userId, int projectId, Role role) {

        String insertProjectUserSQL = "INSERT INTO user_projects_roles (user_id,project_id,  user_role) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(insertProjectUserSQL)) {
            ps.setInt(1, userId);
            ps.setInt(2, projectId);
            ps.setString(3, role.toString());

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY ADDED USER WITH ID: " + userId +
                    " TO THE PROJECT WITH ID: + " + projectId);
        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE ADDING USER TO THE PROJECT");
        }
    }

    @Override
    public void update(Persistable persistable, int id) {
        String query = "UPDATE projects SET name = ?, description = ?, user_id = ?  WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ((Project) persistable).getName());
            ps.setString(2, ((Project) persistable).getDescription());
            ps.setInt(3, ((Project) persistable).getCreatorId());

            if(ps.executeUpdate() == 1) {
                REPO_LOGGER.info("SUCCESSFULLY UPDATED USER: " + persistable.toString());
            }

        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE UPDATING USER");
        }
    }

    @Override
    public void delete(Persistable persistable) {
        int id = ((Project) persistable).getId();

        String query = "DELETE FROM projects WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED PROJECT: " + persistable.toString());
        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT DELETE PROJECT");
        }
    }

    @Override
    public void deleteById(int id) {

        String query = "DELETE FROM projects WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED PROJECT WITH ID: " + id);
        }
        catch(SQLException e) {
            REPO_LOGGER.error("COULD NOT DELETE PROJECT");
        }
    }
}