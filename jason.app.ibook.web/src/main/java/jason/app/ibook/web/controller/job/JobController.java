package jason.app.ibook.web.controller.job;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.IJobService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

    @Autowired(required = false)
    private IJobService jobService;

    @RequestMapping("/index")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getJobCategoryStructure());
        return "job.home";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "category", required = false) String category, @RequestParam(value = "subCategory", required = false) String subCategory) {
        List<ICategory> categories = categoryService.getJobCategoryStructure();

        String query = "";
        if(q!=null) {
           query = q;
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
       model.addAttribute("job", jobService.getJobById(id));
        return "job.detail";
    }
}
