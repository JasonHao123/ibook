package jason.app.ibook.security.service;

import jason.app.ibook.security.api.dao.IRememberMeTokenDao;
import jason.app.ibook.security.api.dao.IUserDao;
import jason.app.ibook.security.api.model.IUser;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.List;

import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class SecurityServiceImpl implements ISecurityService {
    private IUserDao userDao;
    private PasswordEncoder encoder;
    private IRememberMeTokenDao rememberMeDao;
    
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
    public IUser createUser(String username, String password, List<String> roles) {
        // TODO Auto-generated method stub
        return userDao.createUser(username, encoder.encode(password), roles);
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

}
