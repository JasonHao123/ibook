package jason.app.ibook.job.jpa;

import jason.app.ibook.job.api.dao.ICompanyDao;
import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.jpa.entity.CompanyImpl;
import jason.app.ibook.job.jpa.entity.UserCompanyImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CompanyDaoImpl implements ICompanyDao{
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }

    @Override
    public Company createCompany(Company company) {
        // TODO Auto-generated method stub
        CompanyImpl companyImpl = new CompanyImpl();
        companyImpl.setName(company.getName());
        em.persist(companyImpl);
        em.flush();
        company.setId(companyImpl.getId());
        return company;
    }
    
    @Override
    public void createUserAccess(Long companyId,String user) {
        // TODO Auto-generated method stub
        CompanyImpl companyImpl = em.find(CompanyImpl.class, companyId);
        if(companyImpl!=null) {
            UserCompanyImpl uci = new UserCompanyImpl();
            uci.setCompany(companyImpl);
            uci.setUsername(user);
            em.persist(uci);
        }
       
    }

    @Override
    public List<Company> findUserCompanies(String name) {
        Query query = em.createQuery("select uc.company from UserCompanyImpl uc where uc.username=:user");
        query.setParameter("user", name);
        List<CompanyImpl> result = query.getResultList();
        List<Company> companies = new ArrayList<Company>();
        for(CompanyImpl item:result) {
            Company company = new Company();
            company.setId(item.getId());
            company.setName(item.getName());
            companies.add(company);
        }
        return companies;
    }

	@Override
	public Company findById(Long id) {
		// TODO Auto-generated method stub
		CompanyImpl comp = em.find(CompanyImpl.class, id);
		Company company = new Company();
		company.setId(id);
		company.setName(comp.getName());
		return company;
	} 
}
