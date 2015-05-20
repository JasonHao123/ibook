package jason.app.ibook.user.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AgentProfileImpl extends ProfileImpl {
    
    @Column
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
