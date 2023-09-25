package abeczkowska.model;
import abeczkowska.persistence.Persistable;

public class User implements Persistable {
    private int id;
    private String username;
    private String password;
    private Role globalRole;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getGlobalRole() {
        return globalRole;
    }

    public void setGlobalRole(Role globalRole) {
        this.globalRole = globalRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", globalRole=" + globalRole +
                '}';
    }
}
