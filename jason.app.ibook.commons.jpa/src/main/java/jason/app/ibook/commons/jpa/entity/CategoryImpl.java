package jason.app.ibook.commons.jpa.entity;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.constant.LocationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class CategoryImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private CategoryImpl parent;

    
    @Column
    private String name;
    
    @Column
    private CategoryType type;
    
    public CategoryType getType() {
        return type;
    }

    @Column
    private Integer subType;

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryImpl getParent() {
        return parent;
    }

    public void setParent(CategoryImpl parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    


}
