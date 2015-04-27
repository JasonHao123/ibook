package jason.app.ibook.job.api.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import jason.app.ibook.commons.api.model.ICategory;

public class Company {
    private Long id;
    private String name;
    private ICategory location;
    private String address;
    private ICategory industry;
    private ICategory scale;    
    @JsonIgnore
    private ICompany parent;    
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
    public ICategory getLocation() {
        return location;
    }
    public void setLocation(ICategory location) {
        this.location = location;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ICategory getIndustry() {
        return industry;
    }
    public void setIndustry(ICategory industry) {
        this.industry = industry;
    }
    public ICategory getScale() {
        return scale;
    }
    public void setScale(ICategory scale) {
        this.scale = scale;
    }
    public ICompany getParent() {
        return parent;
    }
    public void setParent(ICompany parent) {
        this.parent = parent;
    }
    
}
