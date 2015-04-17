package jason.app.ibook.security.jpa.entity;

import jason.app.ibook.security.api.model.IRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")
public class RoleImpl implements IRole {
    @Id
    @Column(nullable = false)
    private String name;
    
    @Column
    private String label;
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

}
