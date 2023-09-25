package abeczkowska.model;

public class DocumentBuilder {

    private Document document;

    public DocumentBuilder() {
        document = new Document();
    }

    public DocumentBuilder setId(int id) {
        document.setId(id);
        return this;
    }

    public DocumentBuilder setTitle(String title) {
        document.setTitle(title);
        return this;
    }

    public DocumentBuilder setDescription(String description) {
        document.setDescription(description);
        return this;
    }

    public DocumentBuilder setCreatorId(int creatorId) {
        document.setCreatorId(creatorId);
        return this;
    }

    public DocumentBuilder setProjectId(int projectId) {
        document.setProjectId(projectId);
        return this;
    }

    public DocumentBuilder setTopic(String topic) {
        document.setTopic(topic);
        return this;
    }

    public DocumentBuilder setContent(String content) {
        document.setContent(content);
        return this;
    }

    public Document build() {
        return document;
    }

}
