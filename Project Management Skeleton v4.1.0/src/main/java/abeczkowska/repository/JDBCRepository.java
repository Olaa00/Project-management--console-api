package abeczkowska.repository;

import abeczkowska.persistence.Persistable;

import java.util.List;

public interface JDBCRepository {
    public Persistable findById(int id);

    public List<? extends Persistable> findAll();

    public Persistable create(Persistable persistable);

    public void update(Persistable persistable, int id);

    public void delete(Persistable persistable);

    public void deleteById(int id);
}

