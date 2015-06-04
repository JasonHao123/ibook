package jason.app.ibook.job.craweller;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {

	private Task task;

	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	private List<Company> company;


	public List<Company> getCompany() {
		return company;
	}
	public void setCompany(List<Company> company) {
		this.company = company;
	}
	
	
}
