package jason.app.ibook.api.dao;

import jason.app.ibook.api.model.IUser;

import java.util.List;

public interface IUserDao {

    IUser findByUsername(String username);
    public IUser createUser(String username,String password,List<String> roles);

    public List<String> findAllPrincipals();

    public List<String> findAllRoles();
}
