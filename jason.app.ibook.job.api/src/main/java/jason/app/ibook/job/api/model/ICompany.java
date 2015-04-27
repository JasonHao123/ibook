package jason.app.ibook.job.api.model;

import jason.app.ibook.commons.api.model.ICategory;

public interface ICompany {
    public Long getId() ;
   
    public String getName() ;
    public ICategory getLocation();
    public String getAddress();
    public ICategory getIndustry() ;
    public ICategory getScale();
    public ICompany getParent() ;
}
