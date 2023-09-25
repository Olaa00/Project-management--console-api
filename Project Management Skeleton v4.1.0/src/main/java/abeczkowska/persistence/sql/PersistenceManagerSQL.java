package abeczkowska.persistence.sql;

import abeczkowska.connection.DatabaseConn;
import abeczkowska.model.Document;
import abeczkowska.model.Project;
import abeczkowska.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import abeczkowska.persistence.Persistable;
import abeczkowska.persistence.PersistenceManager;
import abeczkowska.persistence.QuerySpec;
import abeczkowska.repository.UserRepository;

import java.sql.Connection;
import java.util.List;

public class PersistenceManagerSQL implements PersistenceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceManagerSQL.class.getName());
    private final Connection conn;
    private final UserRepository  userRepository;

    public PersistenceManagerSQL() {
        conn = DatabaseConn.getInstance().getConnection();
        userRepository = new UserRepository();
    }

    @Override
    public Persistable create(Persistable persistable) {

        if(persistable instanceof User) {

        }

        return persistable;
    }


    @Override
    public void update(Persistable persistable, int id) {

        if(persistable instanceof User) {
            userRepository.update(persistable, id);
        }
        if(persistable instanceof Project) {

        }
        if(persistable instanceof Document) {

        }

    }

    @Override
    public void delete(Persistable persistable) {

        if(persistable instanceof User) {

        }

    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) {
        return null;
    }
}
