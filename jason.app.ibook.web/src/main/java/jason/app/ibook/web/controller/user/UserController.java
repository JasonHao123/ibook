package jason.app.ibook.web.controller.user;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.ICompanyService;
import jason.app.ibook.job.api.service.IJobService;
import jason.app.ibook.web.controller.company.model.CompanyForm;
import jason.app.ibook.web.controller.job.model.JobForm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	  private static final Logger logger = LoggerFactory
	            .getLogger(UserController.class);
	   @Autowired(required=false)
	  private IJobService jobService;
	   
       @Autowired(required=false)
      private ICompanyService companyService;
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
    
    @RequestMapping(value="/company/list",method=RequestMethod.GET)
    @Transactional
    public String listCompany(Model model) {
        List<Company> companies = companyService.findUserCompanies(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("companies", companies);
        return "user.company.list";
    }
    
    
    @RequestMapping(value="/company/add",method=RequestMethod.GET)
    public String addCompany(Model model) {
        CompanyForm form =  new CompanyForm();
        model.addAttribute("form", form);
        return "user.company.add";
    }
    
    @RequestMapping(value="/company/add",method=RequestMethod.POST)
    public String postAddCompany(CompanyForm form, BindingResult result) {
        logger.info(form.getName());
        Company company = new Company();
        company.setName(form.getName());
        if(companyService!=null) {
            companyService.createCompany(company,SecurityContextHolder.getContext().getAuthentication().getName());
        }
            return "redirect:/user/company/list.do";
    }
}
