package jason.app.ibook.security.api.service;

import jason.app.ibook.security.api.model.IUser;

import java.util.List;

import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;


public interface ISecurityService {
    public IUser createUser(String username,String password,List<String> roles);

    public List<String> getAllUsernames();

    public void insertAce(AclImpl acl, Permission permission, PrincipalSid principalSid, boolean b);

    public void removePersistenceAuthenticationByKey(String key);
}
