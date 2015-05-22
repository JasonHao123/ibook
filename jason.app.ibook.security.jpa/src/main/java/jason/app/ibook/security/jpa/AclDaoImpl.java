package jason.app.ibook.security.jpa;

import jason.app.ibook.security.api.dao.IAclDao;
import jason.app.ibook.security.api.model.AclInfo;
import jason.app.ibook.security.api.model.IAclClass;
import jason.app.ibook.security.api.model.IAclEntry;
import jason.app.ibook.security.api.model.IAclObjectIdentity;
import jason.app.ibook.security.api.model.IAclSid;
import jason.app.ibook.security.jpa.entity.AclClassImpl;
import jason.app.ibook.security.jpa.entity.AclEntryImpl;
import jason.app.ibook.security.jpa.entity.AclObjectIdentityImpl;
import jason.app.ibook.security.jpa.entity.AclSidImpl;
import jason.app.ibook.security.jpa.util.BeanUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Repository;

@Repository
public class AclDaoImpl implements IAclDao {
    public final static String DEFAULT_SELECT_CLAUSE = "select acl_object_identity.object_id_identity, "
            + "acl_entry.ace_order,  "
            + "acl_object_identity.id as acl_id, "
            + "acl_object_identity.parent_object, "
            + "acl_object_identity.entries_inheriting, "
            + "acl_entry.id as ace_id, "
            + "acl_entry.mask,  "
            + "acl_entry.granting,  "
            + "acl_entry.audit_success, "
            + "acl_entry.audit_failure,  "
            + "acl_sid.principal as ace_principal, "
            + "acl_sid.sid as ace_sid,  "
            + "acli_sid.principal as acl_principal, "
            + "acli_sid.sid as acl_sid, "
            + "acl_class.class "
            + "from acl_object_identity"
            + "left join acl_sid acli_sid on acli_sid.id = acl_object_identity.owner_sid "
            + "left join acl_class on acl_class.id = acl_object_identity.object_id_class   "
            + "left join acl_entry on acl_object_identity.id = acl_entry.acl_object_identity "
            + "left join acl_sid on acl_entry.sid = acl_sid.id  "
            + "where ( ";
    
    public final static String DEFAULT_SELECT_CLAUSE_UPPER = "select acl_object_identity.object_id_identity, "
            + "acl_entry.ace_order,  "
            + "acl_object_identity.id as acl_id, "
            + "acl_object_identity.parent_object, "
            + "acl_object_identity.entries_inheriting, "
            + "acl_entry.id as ace_id, "
            + "acl_entry.mask,  "
            + "acl_entry.granting,  "
            + "acl_entry.audit_success, "
            + "acl_entry.audit_failure,  "
            + "acl_sid.principal as ace_principal, "
            + "acl_sid.sid as ace_sid,  "
            + "acli_sid.principal as acl_principal, "
            + "acli_sid.sid as acl_sid, "
            + "acl_class.class "
            + "from ACL_OBJECT_IDENTITY acl_object_identity"
            + "left join ACL_SID acli_sid on acli_sid.id = acl_object_identity.owner_sid "
            + "left join ACL_CLASS acl_class on acl_class.id = acl_object_identity.object_id_class   "
            + "left join ACL_ENTRY acl_entry on acl_object_identity.id = acl_entry.acl_object_identity "
            + "left join ACL_SID acl_sid on acl_entry.sid = acl_sid.id  "
            + "where ( ";

        private final static String DEFAULT_LOOKUP_KEYS_WHERE_CLAUSE = "(acl_object_identity.id = ?)";

        private final static String DEFAULT_LOOKUP_IDENTITIES_WHERE_CLAUSE = "(acl_object_identity.object_id_identity = ? and acl_class.class = ?)";

        public final static String DEFAULT_ORDER_BY_CLAUSE = ") order by acl_object_identity.object_id_identity"
            + " asc, acl_entry.ace_order asc";
        
        // SQL Customization fields
        private String selectClause = DEFAULT_SELECT_CLAUSE_UPPER;
        private String lookupPrimaryKeysWhereClause = DEFAULT_LOOKUP_KEYS_WHERE_CLAUSE;
        private String lookupObjectIdentitiesWhereClause = DEFAULT_LOOKUP_IDENTITIES_WHERE_CLAUSE;
        private String orderByClause = DEFAULT_ORDER_BY_CLAUSE;
        

        private String computeRepeatingSql(String repeatingSql, int requiredRepetitions) {
            assert requiredRepetitions > 0 : "requiredRepetitions must be > 0";

            final String startSql = selectClause;

            final String endSql = orderByClause;

            StringBuilder sqlStringBldr =
                new StringBuilder(startSql.length() + endSql.length() + requiredRepetitions * (repeatingSql.length() + 4));
            sqlStringBldr.append(startSql);

            for (int i = 1; i <= requiredRepetitions; i++) {
                sqlStringBldr.append(repeatingSql);

                if (i != requiredRepetitions) {
                    sqlStringBldr.append(" or ");
                }
            }

            sqlStringBldr.append(endSql);

            return sqlStringBldr.toString();
        }
        
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
    public void createObjectIdentity(IAclObjectIdentity identity) {
        // TODO Auto-generated method stub
        em.persist(BeanUtil.toAclObjectIdentityImpl(identity,false));
        em.flush();
    }

    @Override
    public List<IAclSid> findAclSidList(Boolean principal, String sidName) {
        Query query = em.createQuery("select sid from AclSidImpl sid where sid.principal=:principal and sid.sid=:sid");
        query.setParameter("principal", principal);
        query.setParameter("sid", sidName);
        return BeanUtil.toAclSidList(query.getResultList());
    }

    @Override
    public IAclSid createAclSid(IAclSid sid2) {
        AclSidImpl sid = BeanUtil.toAclSidImpl(sid2);
        em.persist(sid);
        em.flush();
        return BeanUtil.toAclSid(sid);
    }

    @Override
    public List<IAclClass> findAclClassList(String type) {
        Query query = em.createQuery("select clazz from AclClassImpl clazz where clazz.clazz=:clazz");

        query.setParameter("clazz", type);
        return BeanUtil.toAclClassList(query.getResultList());
    }

    @Override
    
    public IAclClass createAclClass(IAclClass clazz) {
        AclClassImpl acl = BeanUtil.toAclClassImpl(clazz);
        em.persist(acl);
        em.flush();
        return BeanUtil.toAclClass(acl);
    }

    @Override
    
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
        em.remove(BeanUtil.toAclObjectIdentityImpl(oidPrimaryKey,false));
    }

    @Override
    
    public void createEntries(List<IAclEntry> entries) {
        for(IAclEntry entry:entries) {
            em.persist(BeanUtil.toAclEntryImpl(entry));
        }
        
    }

    @Override
    public boolean updateObjectIdentity(IAclObjectIdentity aclObject) {
        // TODO Auto-generated method stub
         em.merge(BeanUtil.toAclObjectIdentityImpl(aclObject,false));
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

    @Override
    public List<AclInfo> findParentsToLookup(Set<Long> findNow) {
        // TODO Auto-generated method stub
        String sql = computeRepeatingSql(lookupPrimaryKeysWhereClause, findNow.size());
        Query query = em.createNativeQuery(sql);
        int i = 0;
        for (Long toFind : findNow) {
            i++;
            query.setParameter(i, toFind);
        }
        List<Object[]> result = query.getResultList();
        return BeanUtil.toAclInfoList(result);
    }

    @Override
    public List<AclInfo> findParentsToLookup(Collection<ObjectIdentity> objectIdentities) {
        String sql = computeRepeatingSql(lookupObjectIdentitiesWhereClause, objectIdentities.size());

        Query query = em.createNativeQuery(sql);
        int i = 0;
        for (ObjectIdentity oid : objectIdentities) {
            // Determine prepared statement values for this iteration
            String type = oid.getType();

            // No need to check for nulls, as guaranteed non-null by ObjectIdentity.getIdentifier() interface contract
            String identifier = oid.getIdentifier().toString();
            long id = (Long.valueOf(identifier)).longValue();

            // Inject values
            query.setParameter((2 * i) + 1, id);
            query.setParameter((2 * i) + 2, type);
            i++;
        }
        List<Object[]> result = query.getResultList();
        return BeanUtil.toAclInfoList(result);
    }

}
