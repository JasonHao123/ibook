package jason.app.ibook.web.controller.job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job")
public class JobController {
    @RequestMapping("/index")
    public String home() {
        return "job.home";
    }
}
