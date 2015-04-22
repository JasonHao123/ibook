package jason.app.ibook.security.service.web;

import jason.app.ibook.security.api.dao.IRememberMeTokenDao;
import jason.app.ibook.security.api.model.RememberMeAuthentication;
import jason.app.ibook.security.api.service.SpringSecurityHttpSessionListener;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class AdvancedHttpSessionSecurityContextRepository extends HttpSessionSecurityContextRepository {

    private IRememberMeTokenDao authDao;

    private UserDetailsService userDetailService;

    public UserDetailsService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public IRememberMeTokenDao getAuthDao() {
        return authDao;
    }

    public void setAuthDao(IRememberMeTokenDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        // TODO Auto-generated method stub
        SecurityContext context = super.loadContext(requestResponseHolder);
        if (context.getAuthentication() == null) {
            HttpSession session = requestResponseHolder.getRequest().getSession(false);
            if (session != null) {
                String contextKey = (String) session.getAttribute(SpringSecurityHttpSessionListener.PERSIST_SECURITY_CONTEXT_KEY);
                if (contextKey != null) {
                    PersistentRememberMeToken token = authDao.getTokenForSeries(contextKey);
                    if (token != null) {
                        RememberMeAuthentication auth = new RememberMeAuthentication();
                        auth.setToken(token);
                        UserDetails user = userDetailService.loadUserByUsername(auth.getToken().getUsername());
                        if (user != null) {
                            auth.setAuthenticated(true);
                            auth.setName(auth.getToken().getUsername());
                            auth.setAuthorities(user.getAuthorities());
                            auth.setDetails(user);
                            auth.setPrincipal(user);
                            context.setAuthentication(auth);
                        } else {
                            auth.setAuthenticated(false);
                        }
                    }
                }
            }
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        super.saveContext(context, request, response);
        HttpSession session = request.getSession(false);
        if (session != null) {
            String key = (String) session.getAttribute(SpringSecurityHttpSessionListener.PERSIST_SECURITY_CONTEXT_KEY);
            if (key != null) {

            }
        }

    }

    public String getName(Authentication auth) {
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }

        if (auth.getPrincipal() instanceof Principal) {
            return ((Principal) auth.getPrincipal()).getName();
        }

        return (auth.getPrincipal() == null) ? "" : auth.getPrincipal().toString();
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        // TODO Auto-generated method stub
        if (super.containsContext(request))
            return true;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String key = (String) session.getAttribute(SpringSecurityHttpSessionListener.PERSIST_SECURITY_CONTEXT_KEY);
            if (key != null) {
                return authDao.getTokenForSeries(key) != null;
            }
        }
        return false;
    }

}
