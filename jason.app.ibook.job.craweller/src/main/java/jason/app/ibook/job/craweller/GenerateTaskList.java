package jason.app.ibook.job.craweller;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ICategoryService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateTaskList {
	private Logger logger = LoggerFactory.getLogger(ExampleBean.class);
	
	private ICategoryService categoryService;
    public ICategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public TaskList sayHello(String name)  {
    	logger.info("Hello example bean!"+name);
    	TaskList taskList = new TaskList();
    	List<Task> tasks = new ArrayList<Task>();
    	List<ICategory> jobTypes = categoryService.listJobTypes();
    	int id = 0;
    	for(ICategory type:jobTypes) {
    		Task task = new Task();
    		task.setId(id++);
    		task.setType(type.getName());
    		tasks.add(task);
    	}
    	taskList.setTask(tasks);
    	return taskList;
    }
}
