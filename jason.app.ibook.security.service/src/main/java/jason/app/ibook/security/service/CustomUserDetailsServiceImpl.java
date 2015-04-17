package jason.app.ibook.security.service;

import jason.app.ibook.security.api.dao.IUserDao;
import jason.app.ibook.security.api.model.IRole;
import jason.app.ibook.security.api.model.IUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;


/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */

public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;
        if (StringUtils.hasText(username)) {
            IUser dbUser = null;
            try {
                dbUser = userDao.findByUsername(username);
            }catch(Exception e) {
                e.printStackTrace();
            }
            if (dbUser != null) {
                List roles = new ArrayList<SimpleGrantedAuthority>();
                if(dbUser.getRoles()!=null) {
                    for(IRole role:dbUser.getRoles()) {
                        roles.add(new SimpleGrantedAuthority(role.getName()));
                    }
                }
//                if(roles.size()==0) {
//                    roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//                }
                user = new org.springframework.security.core.userdetails.User(username, dbUser.getPassword(), dbUser.isEnabled(), true, true, true, roles);
            }
        }
        if(user==null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

}
