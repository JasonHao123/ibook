package jason.app.ibook.security.api.dao;

import jason.app.ibook.security.api.model.AclInfo;
import jason.app.ibook.security.api.model.IAclClass;
import jason.app.ibook.security.api.model.IAclEntry;
import jason.app.ibook.security.api.model.IAclObjectIdentity;
import jason.app.ibook.security.api.model.IAclSid;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.acls.model.ObjectIdentity;

public interface IAclDao {

    List<ObjectIdentity> findChildren(Serializable identifier, String type);

    IAclObjectIdentity getObjectIdentity(String type, Serializable identifier);

    void createObjectIdentity(IAclObjectIdentity identity);

    List<IAclSid> findAclSidList(Boolean valueOf, String sidName);

    IAclSid createAclSid(IAclSid sid2);

    List<IAclClass> findAclClassList(String type);

    IAclClass createAclClass(IAclClass clazz);

    void deleteEntries(IAclObjectIdentity oidPrimaryKey);

    void deleteObjectIdentity(IAclObjectIdentity oidPrimaryKey);

    void createEntries(List<IAclEntry> entries);

    boolean updateObjectIdentity(IAclObjectIdentity aclObject);

    IAclSid findAclSid(String principal);

    List<AclInfo> findParentsToLookup(Set<Long> findNow);

    List<AclInfo> findParentsToLookup(Collection<ObjectIdentity> objectIdentities);

}
