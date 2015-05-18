package jason.app.ibook.commons.api.model;

import jason.app.ibook.commons.api.constant.CategoryType;

import java.util.List;

public interface ICategory {
    public Long getId() ;
    public String getName();
    public ICategory getParent() ;
    public CategoryType getType();
    public Integer getSubType() ;
    public List<ICategory> getChildren();
    public boolean isLeaf();
}
