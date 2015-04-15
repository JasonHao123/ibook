package jason.app.ibook.security.jpa.util;

import jason.app.ibook.api.model.AclClass;
import jason.app.ibook.api.model.AclEntry;
import jason.app.ibook.api.model.AclObjectIdentity;
import jason.app.ibook.api.model.AclSid;
import jason.app.ibook.api.model.Contact;
import jason.app.ibook.api.model.IAclClass;
import jason.app.ibook.api.model.IAclEntry;
import jason.app.ibook.api.model.IAclObjectIdentity;
import jason.app.ibook.api.model.IAclSid;
import jason.app.ibook.api.model.IContact;
import jason.app.ibook.security.jpa.entity.AclClassImpl;
import jason.app.ibook.security.jpa.entity.AclEntryImpl;
import jason.app.ibook.security.jpa.entity.AclObjectIdentityImpl;
import jason.app.ibook.security.jpa.entity.AclSidImpl;
import jason.app.ibook.security.jpa.entity.ContactImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.acls.model.ObjectIdentity;

public class BeanUtil {

    public static List<ObjectIdentity> toObjectIdentityList(List<AclObjectIdentityImpl> resultList){
        List<ObjectIdentity> result = new ArrayList<ObjectIdentity>();
        if(resultList!=null) {
            for(AclObjectIdentityImpl aclObject:resultList) {
                result.add(toObjectIdentity(aclObject));
            }
        }
        return result;
    }

    public static IAclObjectIdentity toObjectIdentity(AclObjectIdentityImpl aclObject) {
        // TODO Auto-generated method stub
        if(aclObject==null) return null;
        AclObjectIdentity identity = new AclObjectIdentity();
        identity.setId(aclObject.getId());
        identity.setAclEntries(toAclEntryList(aclObject.getAclEntries()));
        identity.setEntriesInheriting(aclObject.getEntriesInheriting());
        identity.setObjIdClass(toAclClass(aclObject.getObjIdClass()));
        identity.setObjIdIdentity(aclObject.getObjIdIdentity());
        identity.setOwner(toAclSid(aclObject.getOwner()));
        identity.setParentObject(toObjectIdentity(aclObject.getParentObject()));

        return identity;
    }

    public static List<IAclEntry> toAclEntryList(List<AclEntryImpl> resultList) {
        List<IAclEntry> result = new ArrayList<IAclEntry>();
        if(resultList!=null) {
            for(AclEntryImpl aclObject:resultList) {
                result.add(toAclEntry(aclObject));
            }
        }
        return result;
    }


    public static IAclEntry toAclEntry(AclEntryImpl aclObject) {
        if(aclObject==null) return null;
        AclEntry entry = new AclEntry();
        entry.setAceOrder(aclObject.getAceOrder());
        entry.setAclObjectIdentity(toObjectIdentity(aclObject.getAclObjectIdentity()));
        entry.setAuditFailure(aclObject.getAuditFailure());
        entry.setAuditSuccess(aclObject.getAuditSuccess());
        entry.setGranting(aclObject.getGranting());
        entry.setId(aclObject.getId());
        entry.setMask(aclObject.getMask());
        entry.setSid(toAclSid(aclObject.getSid()));
        return entry;
    }

    public static List<IAclSid> toAclSidList(List<AclSidImpl> resultList) {
        List<IAclSid> result = new ArrayList<IAclSid>();
        if(resultList!=null) {
            for(AclSidImpl aclObject:resultList) {
                result.add(toAclSid(aclObject));
            }
        }
        return result;
    }

    public static AclObjectIdentityImpl toAclObjectIdentityImpl(IAclObjectIdentity aclObject) {
        if(aclObject==null) return null;
        AclObjectIdentityImpl identity = new AclObjectIdentityImpl();
        identity.setId(aclObject.getId());
        identity.setAclEntries(toAclEntryImplList(aclObject.getAclEntries()));
        identity.setEntriesInheriting(aclObject.getEntriesInheriting());
        identity.setObjIdClass(toAclClassImpl(aclObject.getObjIdClass()));
        identity.setObjIdIdentity(aclObject.getObjIdIdentity());
        identity.setOwner(toAclSidImpl(aclObject.getOwner()));
        identity.setParentObject(toAclObjectIdentityImpl(aclObject.getParentObject()));
        return identity;
    }

    public static List<AclEntryImpl> toAclEntryImplList(List<IAclEntry> resultList) {
        List<AclEntryImpl> result = new ArrayList<AclEntryImpl>();
        if(resultList!=null) {
            for(IAclEntry aclObject:resultList) {
                result.add(toAclEntryImpl(aclObject));
            }
        }
        return result;
    }

    public static AclSidImpl toAclSidImpl(IAclSid sid2) {
        if(sid2==null) return null;
        AclSidImpl sid = new AclSidImpl();
        sid.setId(sid2.getId());
        sid.setPrincipal(sid2.getPrincipal());
        sid.setSid(sid2.getSid());
        return sid;
    }

    public static IAclSid toAclSid(AclSidImpl sid2) {
        if(sid2==null) return null;
        AclSid sid = new AclSid();
        sid.setId(sid2.getId());
        sid.setPrincipal(sid2.getPrincipal());
        sid.setSid(sid2.getSid());
        return sid;
    }

    public static List<IAclClass> toAclClassList(List<AclClassImpl> resultList) {
        List<IAclClass> result = new ArrayList<IAclClass>();
        if(resultList!=null) {
            for(AclClassImpl aclObject:resultList) {
                result.add(toAclClass(aclObject));
            }
        }
        return result;
    }

    public static AclClassImpl toAclClassImpl(IAclClass clazz) {
        if(clazz==null) return null;
        AclClassImpl aclClass = new AclClassImpl();
        aclClass.setClazz(clazz.getClazz());
        aclClass.setId(clazz.getId());
        return aclClass;
    }

    public static IAclClass toAclClass(AclClassImpl clazz) {
        if(clazz==null) return null;
        AclClass aclClass = new AclClass();
        aclClass.setClazz(clazz.getClazz());
        aclClass.setId(clazz.getId());
        return aclClass;
    }

    public static AclEntryImpl toAclEntryImpl(IAclEntry aclObject) {
        if(aclObject==null) return null;
        AclEntryImpl entry = new AclEntryImpl();
        entry.setAceOrder(aclObject.getAceOrder());
        entry.setAclObjectIdentity(toAclObjectIdentityImpl(aclObject.getAclObjectIdentity()));
        entry.setAuditFailure(aclObject.getAuditFailure());
        entry.setAuditSuccess(aclObject.getAuditSuccess());
        entry.setGranting(aclObject.getGranting());
        entry.setId(aclObject.getId());
        entry.setMask(aclObject.getMask());
        entry.setSid(toAclSidImpl(aclObject.getSid()));
        return entry;
    }

    public static ContactImpl toContactImpl(IContact contact) {
        // TODO Auto-generated method stub
        if(contact==null) return null;
        ContactImpl contactImpl = new ContactImpl();
        contactImpl.setId(contact.getId());
        contactImpl.setEmail(contact.getEmail());
        contactImpl.setName(contact.getName());
        return contactImpl;
    }

    public static IContact toContact(ContactImpl contact) {
        if(contact==null) return null;
        Contact contactImpl = new Contact();
        contactImpl.setId(contact.getId());
        contactImpl.setEmail(contact.getEmail());
        contactImpl.setName(contact.getName());
        return contactImpl;
    }

    public static List<IContact> toContactList(List<ContactImpl> resultList) {
        List<IContact> result = new ArrayList<IContact>();
        if(resultList!=null) {
            for(ContactImpl aclObject:resultList) {
                result.add(toContact(aclObject));
            }
        }
        return result;
    }

}
