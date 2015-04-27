package jason.app.ibook.commons.api.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import jason.app.ibook.commons.api.constant.CategoryType;

public class Category implements ICategory {
    private Long id;
    private String name;
    @JsonIgnore
    private ICategory parent;
    private CategoryType type;
    private Integer subType;
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setParent(ICategory parent) {
        this.parent = parent;
    }
    public void setType(CategoryType type) {
        this.type = type;
    }
    public void setSubType(Integer subType) {
        this.subType = subType;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ICategory getParent() {
        return parent;
    }
    public CategoryType getType() {
        return type;
    }
    public Integer getSubType() {
        return subType;
    }
}
