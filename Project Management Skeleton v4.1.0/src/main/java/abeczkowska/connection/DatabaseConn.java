package abeczkowska.connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConn {
    private static final Logger DB_LOGGER = LoggerFactory.getLogger(DatabaseConn.class.getName());

    private static final String DB_URL = "jdbc:mysql://localhost:3306/project_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "pass";

    private static DatabaseConn instance;
    private Connection connection;


    public static DatabaseConn getInstance() {
        if (instance == null) {
            instance = new DatabaseConn();
        }
        return instance;
    }

    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        }
        catch (ClassNotFoundException | SQLException e) {
//            DB_LOGGER.error("AN ERROR OCCURRED DURING DB CONNECTION WITH EXCEPTION: " + e.getClass().getName());
            DB_LOGGER.error("Error occured: {}", e.getClass().getName());
        }

        return connection;
    }

}

