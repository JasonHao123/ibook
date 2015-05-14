package jason.app.ibook.job.api.service;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;
import jason.app.ibook.security.api.annotation.ParamName;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ICompanyService {
    public Department createDepartment(Department department);
    public boolean revokeAccessToCompany(Company company,String user);
    Company createCompany(Company company, String username);
    @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
    public List<Company> findUserCompanies(String name);
    boolean grantAdminAccessToCompany(Long companyId, String user);
    boolean grantReadAccessToCompany(Long companyId, String user);
    @PreAuthorize("hasPermission(#company, read) or hasPermission(#company, admin)")
    @ParamName("company")
    public List<Department> findDepartments( Company company);
}
