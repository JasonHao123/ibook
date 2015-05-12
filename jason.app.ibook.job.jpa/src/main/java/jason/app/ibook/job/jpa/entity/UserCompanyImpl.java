package jason.app.ibook.job.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="USER_COMPANY",uniqueConstraints={@UniqueConstraint(columnNames={"username","COMPANY_ID"})})

public class UserCompanyImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String username;
    
    @ManyToOne
    private CompanyImpl company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CompanyImpl getCompany() {
        return company;
    }

    public void setCompany(CompanyImpl company) {
        this.company = company;
    }
}
