package jason.app.ibook.job.service;

import jason.app.ibook.job.api.dao.ICompanyDao;
import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;
import jason.app.ibook.job.api.service.ICompanyService;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.List;
import org.springframework.security.access.intercept.aspectj.aspect.AnnotationSecurityAspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private MutableAclService aclService;
    
    @Autowired
    private ISecurityService securityService;
    
    public MutableAclService getAclService() {
        return aclService;
    }

    public void setAclService(MutableAclService aclService) {
        this.aclService = aclService;
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

    public ICompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    private AnnotationSecurityAspect test;
    
    @Autowired
    private ICompanyDao companyDao;
    @Override
    @Transactional
    public Company createCompany(Company company,String username) {
        // TODO Auto-generated method stub
        Company company2 =  companyDao.createCompany(company);
        grantAccessToCompany(company2.getId(), username);
        final ObjectIdentity objectIdentity = new ObjectIdentityImpl(Company.class, company2.getId());
        aclService.createAcl(objectIdentity);

        AclImpl acl = (AclImpl) aclService.readAclById(new ObjectIdentityImpl(Company.class,
                                                                              company2.getId()));
        securityService.insertAce(acl, BasePermission.ADMINISTRATION, new PrincipalSid(username), true);
        aclService.updateAcl(acl);
        return company2;
    }

    @Override
    public Department createDepartment(Department department) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean grantAccessToCompany(Long companyId, String user) {
        companyDao.createUserAccess(companyId, user);
        return false;
    }

    @Override
    public boolean revokeAccessToCompany(Company company, String user) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEST')")
    public List<Company> findUserCompanies(String name) {
        // TODO Auto-generated method stub
        return companyDao.findUserCompanies(name);
    }

}
