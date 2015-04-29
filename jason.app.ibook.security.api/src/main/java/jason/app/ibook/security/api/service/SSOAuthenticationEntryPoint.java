package jason.app.ibook.security.api.service;

import jason.app.ibook.security.api.service.SpringSecurityHttpSessionListener;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class SSOAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(SSOAuthenticationEntryPoint.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        logger.info("do filter for sso");

        if(SecurityContextHolder.getContext().getAuthentication()==null) {
            HttpSession session = request.getSession(false);
            if(session!=null) {
                String contextKey = (String) session.getAttribute(SpringSecurityHttpSessionListener.PERSIST_SECURITY_CONTEXT_KEY);
                if(contextKey!=null) {
                        String appUrl = request.getRequestURL().toString();
                        if(request.getQueryString()!=null) {
                            appUrl = appUrl+"?"+request.getQueryString();
                        }
                        response.sendRedirect("http://localhost:8181/security/check.do?token="+contextKey+"&redirect="+URLEncoder.encode(appUrl,"UTF-8"));
                }
            }else {
                logger.warn("No valid session for this request!");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

}
