package jason.app.ibook.web.controller.talent;

import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.user.api.model.Profile;
import jason.app.ibook.user.api.service.IProfileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/talent")
public class TalentController {
    @Autowired(required = false)
    private IJobCategoryService categoryService;

    @Autowired(required = false)
    private IProfileService profileService;

    @RequestMapping("/index")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getJobCategoryStructure());
        return "talent.home";
    }
    
    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "q", required = false) String q){
        List<Profile> jobs = profileService.searchTalent(q);
        model.addAttribute("jobs", jobs);
        return "talent.list";
    }
}
