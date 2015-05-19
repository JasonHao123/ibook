package jason.app.ibook.web.controller.talent;

import jason.app.ibook.commons.api.service.IJobCategoryService;
import jason.app.ibook.job.api.service.IJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/talent")
public class TalentController {
    @Autowired(required = false)
    private IJobCategoryService categoryService;

    @Autowired(required = false)
    private IJobService jobService;

    @RequestMapping("/index")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getJobCategoryStructure());
        return "talent.home";
    }
}
