package abeczkowska.model;

import abeczkowska.persistence.Persistable;

public class Project implements Persistable {
    private int id;
    private String name;
    private String description;
    private int creatorId;

    public Project() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(int id) {
        this.creatorId = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}
