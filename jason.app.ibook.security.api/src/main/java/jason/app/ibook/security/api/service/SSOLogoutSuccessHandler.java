package jason.app.ibook.security.api.service;

import jason.app.ibook.security.api.dao.IRememberMeTokenDao;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SSOLogoutSuccessHandler implements LogoutSuccessHandler {
    private String redirectUrl = "";

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        if(session!=null) {
            String contextKey = (String) session.getAttribute(SpringSecurityHttpSessionListener.PERSIST_SECURITY_CONTEXT_KEY);
            if(contextKey!=null) {
                WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
                IRememberMeTokenDao rememberMeDao = context.getBean(IRememberMeTokenDao.class);
                rememberMeDao.removeUserTokens(contextKey);
               
            }
        }
        String appUrl = request.getRequestURL().toString();
        String context = request.getContextPath();
        String redirect2 = appUrl.substring(0,appUrl.indexOf(context)+context.length())+redirectUrl;
        
        response.sendRedirect("http://localhost:8181/security/logout.do?redirect="+URLEncoder.encode(redirect2,"UTF-8"));
    }

}
