package jason.app.ibook.web.controller.user;

import jason.app.ibook.commons.api.model.Category;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ICategoryService;
import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.ICompanyService;
import jason.app.ibook.job.api.service.IJobService;
import jason.app.ibook.user.api.model.AgentProfile;
import jason.app.ibook.user.api.model.CompanyProfile;
import jason.app.ibook.user.api.model.Profile;
import jason.app.ibook.user.api.model.TalentProfile;
import jason.app.ibook.user.api.service.IProfileService;
import jason.app.ibook.web.controller.company.model.CompanyForm;
import jason.app.ibook.web.controller.company.model.DepartmentForm;
import jason.app.ibook.web.controller.job.model.JobForm;
import jason.app.ibook.web.controller.user.model.ProfileForm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	  private static final Logger logger = LoggerFactory
	            .getLogger(UserController.class);
	   @Autowired(required=false)
	  private IJobService jobService;
	   @Autowired(required=false)
	  private ICategoryService jobTypeService;
	    @Autowired(required=false)
	    private IJobCategoryService categoryService;
       @Autowired(required=false)
      private ICompanyService companyService;
       @Autowired(required=false)
      private IProfileService profileService;
    @RequestMapping("/index")
    public String home() {
        return "user.home";
    }
    
    @RequestMapping("/profile")
    public String profile() {
        return "user.profile";
    }
    
    @RequestMapping(value="/profile/edit",method=RequestMethod.GET)
    public String editProfile(Model model) {
        ProfileForm form =  new ProfileForm();
        model.addAttribute("form", form);
        return "user.profile.edit";
    }
    
    @RequestMapping(value="/profile/edit",method=RequestMethod.POST)
    @Transactional
    public String editProfilePost(HttpServletRequest request,ProfileForm form, BindingResult result) {
        logger.info(form.getName());
        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request,null);

        Profile profile = null;
        if(wrapper.isUserInRole("ROLE_TALENT")) {
            profile = new TalentProfile();
        }else if(wrapper.isUserInRole("ROLE_AGENT")) {
            profile = new AgentProfile();
        }else if(wrapper.isUserInRole("ROLE_COMPANY")) {
            profile = new CompanyProfile();
        }
        
        profile.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        profile.setRealname(form.getName());
        if(profileService!=null) {
            profileService.saveProfile(profile);
        }
            return "redirect:/user/profile.do";
    }
    @RequestMapping(value="/job/post",method=RequestMethod.GET)
    public String postJob(Model model) {
        JobForm form =  new JobForm();
        model.addAttribute("job", form);
        if(jobTypeService!=null) {
            model.addAttribute("jobTypes",jobTypeService.listJobTypes());
            model.addAttribute("educationLevels",jobTypeService.listEducationLevels());
        }
        if(companyService!=null) {
            model.addAttribute("companies",companyService.findUserCompanies(SecurityContextHolder.getContext().getAuthentication().getName()));
        }else {
            List<Company> companies = new ArrayList<Company>();
            Company company = new Company();
            company.setId(1L);
            company.setName("test");
            companies.add(company);
            model.addAttribute("companies",companies);
        }
        if(categoryService!=null) {
            model.addAttribute("categories",categoryService.findByParent(null));
        }else {
            List<ICategory> companies = new ArrayList<ICategory>();
            Category company = new Category();
            company.setId(1L);
            company.setName("test");
            companies.add(company);
            model.addAttribute("categories",companies);
        }        
        return "user.post.job";
    }
    
    @RequestMapping(value="/job/post",method=RequestMethod.POST)
    public String postJobSave(JobForm form, BindingResult result) {
        logger.info(form.getTitle());
        Job job = new Job();
        job.setPublisher(SecurityContextHolder.getContext().getAuthentication().getName());
        job.setTitle(form.getTitle());
        job.setCompanyId(form.getCompany());
        if(job.getCompanyId()!=null) {
            job.setCompanyName(companyService.findCompany(Long.valueOf(form.getCompany())).getName());
        }
        job.setCategoryId(form.getCategory1());
        if(job.getCategoryId()!=null) {
        job.setCategoryName(categoryService.findById(Long.valueOf(form.getCategory1())).getName());
        }
        job.setSubCategoryId(form.getCategory2());
        if(job.getSubCategoryId()!=null) {
        job.setSubCategoryName(categoryService.findById(Long.valueOf(form.getCategory2())).getName());
        }
        job.setDescription(form.getDescription());
        job.setLocation(form.getLocation());
        job.setFeature(form.getFeature());
        job.setRequiredSkill(form.getRequiredSkill());
        job.setDesiredSkill(form.getDesiredSkill());
        if(jobService!=null) {
        		jobService.createJob(job);
        }
    		return "user.home";
    }
    
    @RequestMapping(value="/company/list",method=RequestMethod.GET)
    @Transactional
    public String listCompany(Model model) {
        List<Company> companies = new ArrayList<Company>();
        if(companyService!=null) {
            companies = companyService.findUserCompanies(SecurityContextHolder.getContext().getAuthentication().getName());
        }else {
            Company company = new Company();
            company.setId(1L);
            company.setName("test");
            companies.add(company);
        }
        
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
    
    
    @RequestMapping(value="/department/list",method=RequestMethod.GET)
    @Transactional
    public String listDepartment(Model model,@RequestParam("id") Long id) {
        Company company = new Company();
        company.setId(id);
        List<Department> departments = companyService.findDepartments(company);
        model.addAttribute("departments", departments);
        model.addAttribute("companyId", id);
        return "user.department.list";
    }
    
    
    @RequestMapping(value="/department/add",method=RequestMethod.GET)
    public String addDepartment(Model model,@RequestParam("companyId") Long companyId) {
        DepartmentForm form =  new DepartmentForm();
        form.setCompanyId(companyId);
        model.addAttribute("form", form);
        return "user.department.add";
    }
    
    @RequestMapping(value="/department/add",method=RequestMethod.POST)
    public String postAddDepartment(DepartmentForm form, BindingResult result) {
        logger.info(form.getName());
        Department department = new Department();
        department.setName(form.getName());
        if(form.getCompanyId()!=null) {
            Company company = new Company();
            company.setId(form.getCompanyId());
            department.setCompany(company);
        }
        if(companyService!=null) {
            companyService.createDepartment(department);
        }
            return "redirect:/user/department/list.do?id="+form.getCompanyId();
    }
}
