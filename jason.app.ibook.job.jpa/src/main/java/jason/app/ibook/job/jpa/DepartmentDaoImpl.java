package jason.app.ibook.job.jpa;

import jason.app.ibook.job.api.dao.IDepartmentDao;
import jason.app.ibook.job.api.model.Department;
import jason.app.ibook.job.jpa.entity.CompanyImpl;
import jason.app.ibook.job.jpa.entity.DepartmentImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DepartmentDaoImpl implements IDepartmentDao{
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }

    @Override
    public Department create(Department department) {
        // TODO Auto-generated method stub
        DepartmentImpl impl = new DepartmentImpl();
        impl.setName(department.getName());
        if(department.getCompany()!=null) {
            impl.setCompany(em.find(CompanyImpl.class, department.getCompany().getId()));
        }
        em.persist(impl);
        em.flush();
        department.setId(impl.getId());
        return department;
    }

    @Override
    public List<Department> findByCompanyId(Long name) {
        Query query = em.createQuery("select d from DepartmentImpl d where d.company.id=:id");
        query.setParameter("id", name);
        List<DepartmentImpl> result = query.getResultList();
        List<Department> companies = new ArrayList<Department>();
        for(DepartmentImpl item:result) {
            Department company = new Department();
            company.setId(item.getId());
            company.setName(item.getName());
            companies.add(company);
        }
        return companies;
    }
}
