package jason.app.ibook.web.controller.job;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ICategoryService;
import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.commons.api.service.ILocationService;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.IJobService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired(required = false)
    private IJobCategoryService categoryService;
	   @Autowired(required=false)
	  private ICategoryService jobTypeService;
    @Autowired(required = false)
    private IJobService jobService;
    
    @Autowired(required = false)
    private ILocationService locationService;

    @RequestMapping("/index")
    public String home(Model model) {
    	if(categoryService!=null) {
    		model.addAttribute("categories", categoryService.getJobCategoryStructure());
    	}
    	if(jobTypeService!=null) {
            model.addAttribute("jobTypes",jobTypeService.listJobTypes());
            model.addAttribute("educationLevels",jobTypeService.listEducationLevels());
            model.addAttribute("experiences",jobTypeService.listExperiences());
            model.addAttribute("industries",jobTypeService.getJobIndustryStructure());
            model.addAttribute("companyTypes",jobTypeService.listCompanyTypes());
        }
    	if(locationService!=null) {
    		model.addAttribute("cities",locationService.getCityStructure());
    	}
        return "job.home";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "category", required = false) String category, @RequestParam(value = "subCategory", required = false) String subCategory) {
        List<ICategory> categories = categoryService.getJobCategoryStructure();

        String query = "";
        if(q!=null) {
           query = ""+q;
        }        
        if (category != null) {
            if(query.indexOf("categoryId")<0) {
                if (query.length() > 0) {
                    query = query + " and categoryId:" + category;
                }else {
                    query = query + "categoryId:" + category;
                }
            }
        }
        if (subCategory != null) {
            if(query.indexOf("subCategoryId")<0) {
                if (query.length() > 0) {
                    query = query + " and subCategoryId:" + subCategory;
                }else {
                    query = query + "subCategoryId:" + subCategory;
                }
                
            }
        }

        if(query.length()==0) query = "*";
        
        List<Job> jobs = jobService.search(query,categories);

        model.addAttribute("q", q);
       
        model.addAttribute("jobs", jobs);
        model.addAttribute("categories",categories);
        return "job.list";
    }
    
    @RequestMapping("/detail")
    public String detail(Model model,@RequestParam("id") Long id) {
        model.addAttribute("id", id);
       model.addAttribute("job", jobService.getJobById(id));
        return "job.detail";
    }
}
