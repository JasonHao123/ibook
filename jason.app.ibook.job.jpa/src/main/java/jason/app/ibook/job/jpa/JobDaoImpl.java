package jason.app.ibook.job.jpa;

import javax.persistence.EntityManager;

import jason.app.ibook.job.api.dao.IJobDao;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.jpa.entity.JobImpl;

public class JobDaoImpl implements IJobDao{
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
	@Override
	public Job create(Job job) {
		// TODO Auto-generated method stub
		JobImpl jobImpl = new JobImpl();
		jobImpl.setTitle(job.getTitle());
		jobImpl.setCategory(job.getCategoryId());
		jobImpl.setDescription(job.getDescription());
		jobImpl.setPublisher(job.getPublisher());
		jobImpl.setSubCategory(job.getSubCategoryId());
		em.persist(jobImpl);
		em.flush();
		job.setId(jobImpl.getId());
		return job;
	}

}
