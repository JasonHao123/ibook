package jason.app.ibook.security.service.web.controller;


import jason.app.ibook.security.api.dao.IRememberMeTokenDao;
import jason.app.ibook.security.api.util.SecurityUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SSOController {
    @Autowired
    private IRememberMeTokenDao authDao;


    @RequestMapping(value = "/check", method = RequestMethod.GET)
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("token") String token,@RequestParam("redirect") String redirectUrl) throws Exception {
        authDao.updateToken(token,new Date(),SecurityUtil.getPrincipalName(SecurityContextHolder.getContext().getAuthentication()));
        response.sendRedirect(redirectUrl);
	}
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void handleLogout(HttpServletRequest request,
            HttpServletResponse response,@RequestParam("redirect") String redirectUrl) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null) session.invalidate();
        response.sendRedirect(redirectUrl);
    }

}
