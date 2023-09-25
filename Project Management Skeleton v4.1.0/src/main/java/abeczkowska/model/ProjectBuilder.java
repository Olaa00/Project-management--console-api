package abeczkowska.model;

public class ProjectBuilder {
    private Project project;

    public ProjectBuilder() {
        project = new Project();
    }

    public ProjectBuilder withId(int id) {
        project.setId(id);
        return this;
    }

    public ProjectBuilder withName(String name) {
        project.setName(name);
        return this;
    }

    public ProjectBuilder withDescription(String description) {
        project.setDescription(description);
        return this;
    }

    public ProjectBuilder withCreatorId(int creatorId) {
        project.setCreatorId(creatorId);
        return this;
    }

    public Project build() {
        return this.project;
    }

}