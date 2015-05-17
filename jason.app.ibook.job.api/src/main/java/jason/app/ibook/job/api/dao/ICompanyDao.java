package jason.app.ibook.job.api.dao;

import java.util.List;

import jason.app.ibook.job.api.model.Company;

public interface ICompanyDao {

    Company createCompany(Company company);

    void createUserAccess(Long companyId, String user);

    List<Company> findUserCompanies(String name);

	Company findById(Long company);

}
