package jason.app.ibook.security.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import jason.app.ibook.api.dao.IUserDao;
import jason.app.ibook.api.model.IUser;
import jason.app.ibook.api.service.ISecurityService;

public class SecurityServiceImpl implements ISecurityService {
    private IUserDao userDao;
    private PasswordEncoder encoder;
    
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
    public IUser createUser(String username, String password, List<String> roles) {
        // TODO Auto-generated method stub
        return userDao.createUser(username, encoder.encode(password), roles);
    }

}
