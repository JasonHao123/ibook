package jason.app.ibook.job.craweller;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskList {
	private List<Task> task;

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}
}
