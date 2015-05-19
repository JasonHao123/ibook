package jason.app.ibook.job.api.dao;

import jason.app.ibook.job.api.model.Job;

public interface IJobDao {
	public Job create(Job job);

    public Job find(Long id);
}
