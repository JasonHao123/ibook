package jason.app.ibook.security.jpa;

import jason.app.ibook.api.dao.IAclDao;
import jason.app.ibook.api.model.IAclClass;
import jason.app.ibook.api.model.IAclEntry;
import jason.app.ibook.api.model.IAclObjectIdentity;
import jason.app.ibook.api.model.IAclSid;
import jason.app.ibook.security.jpa.entity.AclClassImpl;
import jason.app.ibook.security.jpa.entity.AclEntryImpl;
import jason.app.ibook.security.jpa.entity.AclObjectIdentityImpl;
import jason.app.ibook.security.jpa.entity.AclSidImpl;
import jason.app.ibook.security.jpa.util.BeanUtil;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AclDaoImpl implements IAclDao {
    private EntityManager em;
 
    public void setEntityManager(EntityManager e) {
        em = e;
    }
    
    @Override
    public List<ObjectIdentity> findChildren(Serializable identifier, String type) {
        Query query = em.createQuery("select aoi from AclObjectIdentityImpl aoi, AclObjectIdentityImpl parent, AclClassImpl aclClass where aoi.parentObject = parent and aoi.objIdClass = aclClass and parent.objIdIdentity = :objIdIdentity and parent.objIdClass = (select acl FROM AclClassImpl acl where acl.clazz = :clazz)");
        query.setParameter("objIdIdentity", identifier);
        query.setParameter("clazz", type);
       
        return BeanUtil.toObjectIdentityList(query.getResultList());
    }

    @Override
    public IAclObjectIdentity getObjectIdentity(String type, Serializable identifier) {
        Query query = em.createQuery("select aoi from AclObjectIdentityImpl aoi, AclClassImpl aclClass where  aoi.objIdIdentity = :objIdIdentity and aoi.objIdClass = aclClass and aclClass.clazz = :clazz");
        query.setParameter("objIdIdentity", identifier);
        query.setParameter("clazz", type);

        return BeanUtil.toObjectIdentity((AclObjectIdentityImpl) query.getSingleResult());
    }

    @Override
    @Transactional
    public void createObjectIdentity(IAclObjectIdentity identity) {
        // TODO Auto-generated method stub
        em.persist(BeanUtil.toAclObjectIdentityImpl(identity));
    }

    @Override
    public List<IAclSid> findAclSidList(Boolean principal, String sidName) {
        Query query = em.createQuery("select sid from AclSidImpl sid where sid.principal=:principal and sid.sid=:sid");
        query.setParameter("principal", principal);
        query.setParameter("sid", sidName);
        return BeanUtil.toAclSidList(query.getResultList());
    }

    @Override
    @Transactional
    public IAclSid createAclSid(IAclSid sid2) {
        AclSidImpl sid = BeanUtil.toAclSidImpl(sid2);
        em.persist(sid);
        return BeanUtil.toAclSid(sid);
    }

    @Override
    public List<IAclClass> findAclClassList(String type) {
        Query query = em.createQuery("select clazz from AclClassImpl clazz where clazz.clazz=:clazz");

        query.setParameter("clazz", type);
        return BeanUtil.toAclClassList(query.getResultList());
    }

    @Override
    @Transactional
    public IAclClass createAclClass(IAclClass clazz) {
        AclClassImpl acl = BeanUtil.toAclClassImpl(clazz);
        em.persist(acl);
        return BeanUtil.toAclClass(acl);
    }

    @Override
    @Transactional
    public void deleteEntries(IAclObjectIdentity objectIdentity) {
        AclObjectIdentityImpl objectIdentity2 = em.find(AclObjectIdentityImpl.class, objectIdentity.getId());
        if(objectIdentity2.getEntries()!=null) {
            for(AclEntryImpl entry:objectIdentity2.getEntries()) {
                em.remove(entry);
            }
        }
      
    }

    @Override
    public void deleteObjectIdentity(IAclObjectIdentity oidPrimaryKey) {
        // TODO Auto-generated method stub
        em.remove(BeanUtil.toAclObjectIdentityImpl(oidPrimaryKey));
    }

    @Override
    @Transactional
    public void createEntries(List<IAclEntry> entries) {
        for(IAclEntry entry:entries) {
            em.persist(BeanUtil.toAclEntryImpl(entry));
        }
        
    }

    @Override
    public boolean updateObjectIdentity(IAclObjectIdentity aclObject) {
        // TODO Auto-generated method stub
         em.merge(BeanUtil.toAclObjectIdentityImpl(aclObject));
         return true;
    }

    @Override
    public IAclSid findAclSid(String principal) {
        Query query = em.createQuery("select sid from AclSidImpl sid where sid.sid=:sid");

        query.setParameter("sid", principal);
        List<AclSidImpl> results = query.getResultList();
        if(results.size()>0) {
            return BeanUtil.toAclSid(results.get(0));
        }
        return null;
    }

}
