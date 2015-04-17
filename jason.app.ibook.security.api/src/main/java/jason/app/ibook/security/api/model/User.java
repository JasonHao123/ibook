package jason.app.ibook.security.api.model;

import java.util.List;

public class User implements IUser {
    private String username;

    private String password;
    private boolean enabled;
    private List<IRole> roles;
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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


    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return enabled;
    }


    @Override
    public List<IRole> getRoles() {
        // TODO Auto-generated method stub
        return roles;
    }


    public void setRoles(List<IRole> roles) {
        this.roles = roles;
    }

}
