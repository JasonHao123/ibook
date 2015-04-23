package jason.app.ibook.security.jpa;

import jason.app.ibook.security.api.dao.IRememberMeTokenDao;
import jason.app.ibook.security.jpa.entity.RememberMeTokenImpl;
import jason.app.ibook.security.jpa.entity.UserImpl;
import jason.app.ibook.security.jpa.util.BeanUtil;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

public class RememberMeTokenDaoImpl implements IRememberMeTokenDao {
    private EntityManager em;
    
    public RememberMeTokenDaoImpl() {
    }
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        // TODO Auto-generated method stub
        RememberMeTokenImpl token2 = BeanUtil.toRemberMeTokenImpl(token);
        if(token.getUsername()!=null) {
            token2.setUser(em.find(UserImpl.class, token.getUsername()));
        }
        em.persist(token2);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        // TODO Auto-generated method stub
        RememberMeTokenImpl token = em.find(RememberMeTokenImpl.class, series);
        if(token!=null) {
            token.setTokenValue(tokenValue);
            token.setDate(lastUsed);
            em.merge(token);
        }
        
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        // TODO Auto-generated method stub
        return BeanUtil.toPersistentRememberMeToken(em.find(RememberMeTokenImpl.class, seriesId));
    }

    @Override
    public void removeUserTokens(String series) {
        // TODO Auto-generated method stub
        if(series.endsWith("==")) {
            RememberMeTokenImpl token = em.find(RememberMeTokenImpl.class, series);
            if(token!=null) {
                em.remove(token);
            }
        }else {
            Query query = em.createQuery("select t from RememberMeTokenImpl t where t.user.username = :user and t.tokenValue is not null");
            query.setParameter("user", series);
            List<RememberMeTokenImpl> result = query.getResultList();
            for(RememberMeTokenImpl token:result) {
                em.remove(token);
            }
        }
    }

    @Override
    public void updateToken(String series, Date lastUsed, String username) {
        // TODO Auto-generated method stub
        RememberMeTokenImpl token = em.find(RememberMeTokenImpl.class, series);
        if(token!=null) {
            if(username!=null) {                
                token.setUser(em.find(UserImpl.class, username));
            }
            token.setDate(lastUsed);
            em.merge(token);
        }else {
            token = new RememberMeTokenImpl();
            token.setSeries(series);
            if(username!=null) {
                token.setUser(em.find(UserImpl.class, username));
            }
            token.setDate(lastUsed);
            em.persist(token);
        }
       
    }

}
