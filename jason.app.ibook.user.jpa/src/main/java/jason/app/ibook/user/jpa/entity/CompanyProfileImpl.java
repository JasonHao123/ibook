package jason.app.ibook.user.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CompanyProfileImpl extends ProfileImpl {
    
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
