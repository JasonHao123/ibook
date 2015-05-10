package jason.app.ibook.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/index")
    public String home() {
        return "user.home";
    }
    
    @RequestMapping(value="/job/post",method=RequestMethod.GET)
    public String postJob() {
        return "user.post.job";
    }
    
    @RequestMapping(value="/job/post",method=RequestMethod.POST)
    public String postJobSave() {
        return "user.home";
    }
}
