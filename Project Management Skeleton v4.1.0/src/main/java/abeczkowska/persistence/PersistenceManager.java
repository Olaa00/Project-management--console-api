package abeczkowska.persistence;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable);

	void update(Persistable persistable, int id);

	void delete(Persistable persistable);

	List<Persistable> find(QuerySpec querySpec);
      
}
