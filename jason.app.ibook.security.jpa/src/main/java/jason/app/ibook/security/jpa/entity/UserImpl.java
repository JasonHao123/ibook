package jason.app.ibook.security.jpa.entity;

import jason.app.ibook.security.api.model.IRole;
import jason.app.ibook.security.api.model.IUser;
import jason.app.ibook.security.api.model.Role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="USER")
public class UserImpl implements IUser {
    /** The author's email address */
    @Id
    @Column(nullable = false)
    private String username;
    
    @Column
    private String password;
    
    /** The blog entries posted by this user */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLES")
  
    private List<RoleImpl> roles;

    public void setRoles(List<RoleImpl> roles) {
        this.roles = roles;
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
    @Transient
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<IRole> getRoles() {
        // TODO Auto-generated method stub
        List<IRole> roles2 = new ArrayList<IRole>();
        if(roles!=null) {
            for(RoleImpl roleImpl:this.roles) {
                Role role = new Role();
                role.setName(roleImpl.getName());
                roles2.add(role);
            }
        }
        return roles2;
    }
}
