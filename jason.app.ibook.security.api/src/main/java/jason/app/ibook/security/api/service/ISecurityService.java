package jason.app.ibook.security.api.service;

import jason.app.ibook.security.api.exception.UserAlreadyExistException;
import jason.app.ibook.security.api.model.IUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;


public interface ISecurityService {
    public IUser createUser(String username,String password,List<String> roles) throws UserAlreadyExistException;

    public List<String> getAllUsernames();

    public void insertAce(AclImpl acl, Permission permission, PrincipalSid principalSid, boolean b);

    public void removePersistenceAuthenticationByKey(String key);

    public void login(HttpServletRequest request, HttpServletResponse response, String username, String password);
}
