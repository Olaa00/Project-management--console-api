package abeczkowska.config;

import abeczkowska.action.Action;
import abeczkowska.persistence.PersistenceManager;
import abeczkowska.persistence.sql.PersistenceManagerSQL;

import java.util.List;
public class Config {
    private PersistenceManager persistenceManager;
    private List<Action> actions;

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Config setActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Config withSqlPersistence() {
        setPersistenceManager(new PersistenceManagerSQL());
        return this;
    }

    public Config withXmlPersistence() {
        // TODO
        return this;
    }
}
