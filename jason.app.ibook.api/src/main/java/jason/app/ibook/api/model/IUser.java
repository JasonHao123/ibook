package jason.app.ibook.api.model;

import java.util.List;

public interface IUser {
    public String getUsername();
    public String getPassword();
    public boolean isEnabled();
    public List<IRole> getRoles();
}
