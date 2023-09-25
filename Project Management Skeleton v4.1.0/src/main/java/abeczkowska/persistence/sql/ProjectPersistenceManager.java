package abeczkowska.persistence.sql;

import abeczkowska.persistence.Persistable;
import abeczkowska.persistence.QuerySpec;

import java.util.List;

public class ProjectPersistenceManager extends PersistenceManagerSQL {
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
    public List<Persistable> find(QuerySpec querySpec) {
        return null;
    }
}
