package jason.app.ibook.api.service;

import jason.app.ibook.api.model.IUser;

import java.util.List;

public interface ISecurityService {
    public IUser createUser(String username,String password,List<String> roles);
}
