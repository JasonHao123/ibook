package jason.app.ibook.web.controller.user;

import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.IJobService;
import jason.app.ibook.web.controller.job.model.JobForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	  private static final Logger logger = LoggerFactory
	            .getLogger(UserController.class);
	   @Autowired
	  private IJobService jobService;
    @RequestMapping("/index")
    public String home() {
        return "user.home";
    }
    
    @RequestMapping(value="/job/post",method=RequestMethod.GET)
    public String postJob(Model model) {
        JobForm form =  new JobForm();
        model.addAttribute("job", form);
        return "user.post.job";
    }
    
    @RequestMapping(value="/job/post",method=RequestMethod.POST)
    public String postJobSave(JobForm form, BindingResult result) {
        logger.info(form.getTitle());
        Job job = new Job();
        job.setTitle(form.getTitle());
        if(jobService!=null) {
        		jobService.createJob(job);
        }
    		return "user.home";
    }
}
