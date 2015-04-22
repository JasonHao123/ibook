package jason.app.ibook.security.api.dao;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


public interface IRememberMeTokenDao extends PersistentTokenRepository{

    void updateToken(String series, Date lastUsed,String username);

}
