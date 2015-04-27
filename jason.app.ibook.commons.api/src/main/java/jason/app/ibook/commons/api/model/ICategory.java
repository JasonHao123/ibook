package jason.app.ibook.commons.api.model;

import jason.app.ibook.commons.api.constant.CategoryType;

public interface ICategory {
    public Long getId() ;
    public String getName();
    public ICategory getParent() ;
    public CategoryType getType();
    public Integer getSubType() ;
}
