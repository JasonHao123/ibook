package jason.app.ibook.job.jpa;

import jason.app.ibook.job.api.dao.IJobDao;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.jpa.entity.CompanyImpl;
import jason.app.ibook.job.jpa.entity.JobImpl;

import javax.persistence.EntityManager;

public class JobDaoImpl implements IJobDao{
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
	@Override
	public Job create(Job job) {
		// TODO Auto-generated method stub
		JobImpl jobImpl = new JobImpl();
		if(job.getCompanyId()!=null) {
		    jobImpl.setCompany(em.find(CompanyImpl.class, job.getCompanyId()));
		}
		jobImpl.setTitle(job.getTitle());
		jobImpl.setCategory(job.getCategoryId());
		jobImpl.setDescription(job.getDescription());
		jobImpl.setPublisher(job.getPublisher());
		jobImpl.setSubCategory(job.getSubCategoryId());
		em.persist(jobImpl);
		em.flush();
		job.setId(Long.toString(jobImpl.getId()));
		return job;
	}
    @Override
    public Job find(Long id) {
        // TODO Auto-generated method stub
        JobImpl impl = em.find(JobImpl.class, id);
        if(impl!=null) {
            Job job = new Job();
            job.setCategoryId(impl.getCategory());
         //   job.setCompanyId(impl.getCompany().getId());
            job.setCompanyName(impl.getCompany().getName());
            job.setDescription(impl.getDescription());
            job.setTitle(impl.getTitle());
            return job;
        }return null;
    }

}
