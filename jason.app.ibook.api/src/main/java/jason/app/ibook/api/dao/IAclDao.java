package jason.app.ibook.api.dao;

import jason.app.ibook.api.model.IAclClass;
import jason.app.ibook.api.model.IAclEntry;
import jason.app.ibook.api.model.IAclObjectIdentity;
import jason.app.ibook.api.model.IAclSid;

import java.io.Serializable;
import java.util.List;

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

}
