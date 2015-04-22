package jason.app.ibook.security.api.service;

import java.security.SecureRandom;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringSecurityHttpSessionListener  implements HttpSessionListener{
    private SecureRandom random = new SecureRandom();
    private int seriesLength = 16;
    public static final String PERSIST_SECURITY_CONTEXT_KEY = "PERSIST_SECURITY_CONTEXT_KEY";
    private Logger logger = LoggerFactory.getLogger(SpringSecurityHttpSessionListener.class);
    
    protected String generateSeriesData() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries)).replace(" ", "");
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        HttpSession session = se.getSession();
        if(session!=null) {
            session.setAttribute(PERSIST_SECURITY_CONTEXT_KEY,generateSeriesData());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
        HttpSession session = se.getSession();
        if(context!=null && session!=null) {
            String key = (String) session.getAttribute(PERSIST_SECURITY_CONTEXT_KEY);
            PersistentTokenRepository securityService = context.getBean(PersistentTokenRepository.class);
            if(key!=null && securityService!=null) {
                logger.info("remove persisted security context "+key);
                securityService.removeUserTokens(key);
            }else {
                logger.info("No security service is available!");
            }
        }else {
            logger.info("No Application context available or session is null");
        }
    }

}
