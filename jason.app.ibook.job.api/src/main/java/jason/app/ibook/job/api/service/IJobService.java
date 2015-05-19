package jason.app.ibook.job.api.service;

import java.util.List;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.job.api.model.Job;

public interface IJobService {
	public Job createJob(Job job);

    public List<Job> search(String query, List<ICategory> categories);

    public Job getJobById(Long id);
}
