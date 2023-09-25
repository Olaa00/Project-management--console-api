package abeczkowska.repository;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.Document;
import abeczkowska.persistence.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository implements JDBCRepository {
    private static final Logger REPO_LOGGER = LoggerFactory.getLogger(ProjectRepository.class.getName());
    private final Connection connection;

    public DocumentRepository() {
        this.connection = DatabaseConn.getInstance().getConnection();
    }

    @Override
    public Persistable findById(int id) {
        Document document = null;
        String sqlQuery = "SELECT * FROM documents WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                document = new Document();

                document.setId(resultSet.getInt("id"));
                document.setTitle(resultSet.getString("title"));
                document.setDescription(resultSet.getString("description"));
                document.setCreatorId(resultSet.getInt("creator_id"));
                document.setProjectId(resultSet.getInt("project_id"));
                document.setTitle(resultSet.getString("topic"));
                document.setContent(resultSet.getString("content"));




                REPO_LOGGER.info("FOUND DOCUMENT: \n" + document.toString());
            }

        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WITH EXCEPTION: " + e.getMessage());
        }

        return document;
    }

    @Override
    public List<? extends Persistable> findAll() {
        String sqlQuery = "SELECT * FROM documents";
        List<Document> documents = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Document document = new Document();

                document.setId(resultSet.getInt("id"));
                document.setTitle(resultSet.getString("title"));
                document.setDescription(resultSet.getString("description"));
                document.setCreatorId(resultSet.getInt("creator_id"));
                document.setProjectId(resultSet.getInt("project_id"));
                document.setTitle(resultSet.getString("topic"));
                document.setContent(resultSet.getString("content"));



                documents.add(document);
            }

            REPO_LOGGER.info("NUMBER OF DOCUMENTS FOUND: " + documents.size() );

        }
        catch(SQLException e) {
            // sl4fj
            REPO_LOGGER.error("AN ERROR OCCURRED WITH EXCEPTION:: " + e.getMessage());
        }

        return documents;
    }

    @Override
    public Persistable create(Persistable persistable) {

        String query = "INSERT INTO documents (title, description, creator_id, project_id, topic, content) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ((Document) persistable).getTitle());
            ps.setString(2, ((Document) persistable).getDescription());
            ps.setInt(3, ((Document) persistable).getCreatorId());
            ps.setInt(6, ((Document) persistable).getProjectId());
            ps.setString(4, ((Document) persistable).getTopic());
            ps.setString(5, ((Document) persistable).getContent());


            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY CREATED DOCUMENT: " + persistable.toString());
        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE CREATING THE DOCUMENT");
        }

        return persistable;
    }

    @Override
    public void update(Persistable persistable, int id) {
        String query = "UPDATE documents SET title = ?, description = ?, topic = ?, content = ? WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ((Document) persistable).getTitle());
            ps.setString(2, ((Document) persistable).getDescription());
            ps.setString(3, ((Document) persistable).getTopic());
            ps.setString(4, ((Document) persistable).getContent());
            ps.setInt(5, id);

            if(ps.executeUpdate() == 1) {
                REPO_LOGGER.info("SUCCESSFULLY UPDATED A DOCUMENT: " + persistable.toString());
            }

        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE UPDATING THE DOCUMENT");
        }
    }

    @Override
    public void delete(Persistable persistable) {
        int id = ((Document) persistable).getId();

        String query = "DELETE FROM documents WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED DOCUMENT: " + persistable.toString());
        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE DELETING THE DOCUMENT");
        }
    }

    @Override
    public void deleteById(int id) {

        String query = "DELETE FROM users WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            if(ps.execute()) REPO_LOGGER.info("SUCCESSFULLY DELETED DOCUMENT WITH ID: " + id);
        }
        catch(SQLException e) {
            REPO_LOGGER.error("AN ERROR OCCURRED WHILE DELETING THE DOCUMENT");
        }
    }
}

