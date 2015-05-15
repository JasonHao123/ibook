package jason.app.ibook.job.api.model;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Company {
    private Long id;
    private String name;
 
    @JsonIgnore
    private Company parent;    
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Company getParent() {
        return parent;
    }
    public void setParent(Company parent) {
        this.parent = parent;
    }
    
}
