package jason.app.ibook.security.service;

import jason.app.ibook.security.api.dao.IRememberMeTokenDao;
import jason.app.ibook.security.api.dao.IUserDao;
import jason.app.ibook.security.api.exception.UserAlreadyExistException;
import jason.app.ibook.security.api.model.IUser;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

public class SecurityServiceImpl implements ISecurityService {
    private IUserDao userDao;
    private PasswordEncoder encoder;
    private IRememberMeTokenDao rememberMeDao;
    private AuthenticationProvider authenticationProvider;
    
    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
    public IRememberMeTokenDao getRememberMeDao() {
        return rememberMeDao;
    }
    public void setRememberMeDao(IRememberMeTokenDao rememberMeDao) {
        this.rememberMeDao = rememberMeDao;
    }
    public PasswordEncoder getEncoder() {
        return encoder;
    }
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
    public IUserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public IUser createUser(String username, String password, List<String> roles) throws UserAlreadyExistException{
        // TODO Auto-generated method stub
        return userDao.createUser(username, encoder.encode(password), roles,true);
    }
    @Override
    public List<String> getAllUsernames() {
        // TODO Auto-generated method stub
        return userDao.findAllPrincipals();
    }
    @Override
    public void insertAce(AclImpl acl, Permission permission, PrincipalSid principalSid, boolean granting) {
        // TODO Auto-generated method stub
        acl.insertAce(acl.getEntries().size(), permission, principalSid, granting);
    }
    @Override
    public void removePersistenceAuthenticationByKey(String key) {
        // TODO Auto-generated method stub
        rememberMeDao.removeUserTokens(key);
    }
    @Override
    public void login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        // TODO Auto-generated method stub
        try {
            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            token.setDetails(new WebAuthenticationDetails(request));

            Authentication authentication = authenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

}
