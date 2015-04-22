package jason.app.ibook.security.service.web;

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
//                    PersistentRememberMeToken token= rememberMeDao.getTokenForSeries(contextKey);
//                    if(token!=null) {
//                        // perform auto logon;
//                        logger.info("perform auto logon for "+token.getUsername());
//                        RememberMeAuthentication auth = new RememberMeAuthentication();
//                        auth.setToken(token);
//                        Authentication auth2 = provider.authenticate(auth );
//                        SecurityContextHolder.getContext().setAuthentication(auth2);
//                    }else {
                        String appUrl = request.getRequestURL().toString();
                        response.sendRedirect("http://localhost:8181/security/check.do?token="+contextKey+"&redirect="+URLEncoder.encode(appUrl));
 
//                    }
                }
            }else {
                logger.warn("No valid session for this request!");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

}
