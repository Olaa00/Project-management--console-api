package abeczkowska.model;

import abeczkowska.persistence.Persistable;

public class UserAction implements Persistable {

    private int id;
    private int userId;
    private int projectId;
    private Role userRole;

    public UserAction() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserProjectAssociation{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", userRole=" + userRole +
                '}';
    }
}

