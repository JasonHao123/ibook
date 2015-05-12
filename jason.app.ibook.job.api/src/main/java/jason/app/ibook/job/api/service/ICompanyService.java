package jason.app.ibook.job.api.service;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;

public interface ICompanyService {
    public Department createDepartment(Department department);
    public boolean revokeAccessToCompany(Company company,String user);
    Company createCompany(Company company, String username);
    boolean grantAccessToCompany(Long companyId, String user);
    @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
    public List<Company> findUserCompanies(String name);
}
