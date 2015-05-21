package jason.app.ibook.web.controller.agent;

import java.util.List;

import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.job.api.service.IJobService;
import jason.app.ibook.user.api.model.Profile;
import jason.app.ibook.user.api.service.IProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agent")
public class AgentController {
    @Autowired(required = false)
    private IJobCategoryService categoryService;

    @Autowired(required = false)
    private IProfileService profileService;

    @RequestMapping("/index")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getJobCategoryStructure());
        return "agent.home";
    }
    @RequestMapping("/profile")
    public String profile(Model model,@RequestParam(value="id",required=false) String id) {
        model.addAttribute("id", id);
        return "agent.profile";
    }
    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "q", required = false) String q){
        List<Profile> jobs = profileService.searchAgent(q);
        model.addAttribute("jobs", jobs);
        return "agent.list";
    }
}
