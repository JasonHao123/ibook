package jason.app.ibook.security.jpa;

import jason.app.ibook.security.api.dao.IUserDao;
import jason.app.ibook.security.api.model.IRole;
import jason.app.ibook.security.api.model.IUser;
import jason.app.ibook.security.api.model.Role;
import jason.app.ibook.security.api.model.User;
import jason.app.ibook.security.jpa.entity.RoleImpl;
import jason.app.ibook.security.jpa.entity.UserImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl implements IUserDao {
    private EntityManager em;
    
    public UserDaoImpl() {
    }

    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
    
    public IUser createUser(String username,String password,List<String> roles) {
        UserImpl a = new UserImpl();
        a.setUsername(username);
        a.setPassword(password);
        if(roles!=null) {
            List<RoleImpl> roles2 = new ArrayList<RoleImpl>();
            for(String role:roles) {
                RoleImpl impl = em.find(RoleImpl.class,role);
                if(impl==null) {
                    impl = new RoleImpl();
                    impl.setName(role);
                    em.persist(impl);
                }
                roles2.add(impl);
            }
            a.setRoles(roles2);
        }
        em.persist(a);
        return a;
    }


    @Override
    public IUser findByUsername(String username) {
        // TODO Auto-generated method stub
        UserImpl user =  em.find(UserImpl.class, username);
        if(user!=null) {
            User usr = new User();
            usr.setUsername(user.getUsername());
            usr.setPassword(user.getPassword());
            usr.setEnabled(true);
            if(user.getRoles()!=null) {
                List<IRole> roles = new ArrayList<IRole>();
                for(IRole role:user.getRoles()) {
                    Role role2 = new Role();
                    role2.setName(role.getName());
                    roles.add(role2);
                }
                usr.setRoles(roles);
            }
            return usr;
        }
        return null;
    }

    
    @Override
    public List<String> findAllPrincipals() {

        Query query = em.createQuery("select u.username from UserImpl u", String.class);
        return query.getResultList();
    }



    @Override
    public List<String> findAllRoles() {
        Query query = em.createQuery("select u.name from RoleImpl u", String.class);
        return query.getResultList();
    }
}
