package jason.app.ibook.web.controller.login;

import jason.app.ibook.web.controller.login.validator.UserValidator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    
    private final Validator validator = new UserValidator();
    
    /**
     * The public index page, used for unauthenticated users.
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView displayLogin() {
       return new ModelAndView("login");
    }
    

}
