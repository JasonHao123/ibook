package jason.app.ibook.job.api.service;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;

public interface ICompanyService {
    public Company createCompany(Company company);
    public Department createDepartment(Department department);
    public boolean grantAccessToCompany(Company company,String user);
    public boolean revokeAccessToCompany(Company company,String user);
}
