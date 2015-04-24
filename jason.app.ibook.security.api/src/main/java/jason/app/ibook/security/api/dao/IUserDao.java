package jason.app.ibook.security.api.dao;

import jason.app.ibook.security.api.model.IUser;

import java.util.List;


public interface IUserDao {

    IUser findByUsername(String username);
    public IUser createUser(String username,String password,List<String> roles,boolean enabled);

    public List<String> findAllPrincipals();

    public List<String> findAllRoles();
}
