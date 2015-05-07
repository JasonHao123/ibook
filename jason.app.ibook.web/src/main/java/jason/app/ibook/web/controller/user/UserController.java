package jason.app.ibook.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/index")
    public String home() {
        return "user.home";
    }
    
    @RequestMapping("/job/post")
    public String postJob() {
        return "user.post.job";
    }
}
