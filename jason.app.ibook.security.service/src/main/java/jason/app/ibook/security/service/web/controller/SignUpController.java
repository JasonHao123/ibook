package jason.app.ibook.security.service.web.controller;

import jason.app.ibook.security.api.exception.UserAlreadyExistException;
import jason.app.ibook.security.api.model.User;
import jason.app.ibook.security.api.service.ISecurityService;
import jason.app.ibook.security.service.web.model.SignupForm;
import jason.app.ibook.security.service.web.validator.SignupValidator;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    
    private final Validator validator = new SignupValidator();
    
    @Autowired
    private ISecurityService facade;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    private static final Logger logger = LoggerFactory
            .getLogger(SignUpController.class);
    
    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public ModelAndView displayWelcomePage(HttpSession session) {

       SignupForm User =  new SignupForm();
      
       ModelAndView model = new ModelAndView("signup", "signupForm", User);
     
       return model;
    }
    
    
    /**
     * Selects the home page and populates the model with a message
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @Transactional
    public String postSignUp(HttpServletRequest request,HttpServletResponse response,HttpSession session,SignupForm signupForm, BindingResult result) {
        logger.info("Welcome home!");
        validator.validate(signupForm, result);
        if (result.hasErrors()) {
            signupForm.setPassword("");
            signupForm.setPasswordAgain("");         
            return "signup";
        }

        try {
            userDetailsService.loadUserByUsername(signupForm.getUsername());
            result.rejectValue("username", "validate.err.username", "User is already exist!");
            signupForm.setPassword("");
            signupForm.setPasswordAgain("");         
            return "signup";
        }catch(UsernameNotFoundException e) {

            try {
                facade.createUser(signupForm.getUsername(), signupForm.getPassword(), Arrays.asList(new String[]{"ROLE_USER"}));
                // password encoded in signup, need to reset again
                //user.setPassword(signupForm.getPassword());
                facade.login(request,response,signupForm.getUsername(), signupForm.getPassword());
            } catch (UserAlreadyExistException ee) {
                result.rejectValue("username", "validate.err.username", "User is already exist!");
                signupForm.setPassword("");
                signupForm.setPasswordAgain("");         
                return "signup";
            }

        }
        
        return "redirect:/";
    }
}
