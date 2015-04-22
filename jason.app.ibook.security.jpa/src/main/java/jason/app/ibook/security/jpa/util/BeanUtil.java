package jason.app.ibook.security.jpa.util;

import jason.app.ibook.security.api.model.AclClass;
import jason.app.ibook.security.api.model.AclEntry;
import jason.app.ibook.security.api.model.AclInfo;
import jason.app.ibook.security.api.model.AclObjectIdentity;
import jason.app.ibook.security.api.model.AclSid;
import jason.app.ibook.security.api.model.IAclClass;
import jason.app.ibook.security.api.model.IAclEntry;
import jason.app.ibook.security.api.model.IAclObjectIdentity;
import jason.app.ibook.security.api.model.IAclSid;
import jason.app.ibook.security.jpa.entity.AclClassImpl;
import jason.app.ibook.security.jpa.entity.AclEntryImpl;
import jason.app.ibook.security.jpa.entity.AclObjectIdentityImpl;
import jason.app.ibook.security.jpa.entity.AclSidImpl;
import jason.app.ibook.security.jpa.entity.RememberMeTokenImpl;
import jason.app.ibook.security.jpa.entity.RoleImpl;
import jason.app.ibook.security.jpa.entity.UserImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

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
        if(aclObject==null) return null;
        // TODO Auto-generated method stub
        AclObjectIdentity identity = new AclObjectIdentity();
        identity.setId(aclObject.getId());
        identity.setAclEntries(toAclEntryList(aclObject.getAclEntries(),identity));
        identity.setEntriesInheriting(aclObject.getEntriesInheriting());
        identity.setObjIdClass(toAclClass(aclObject.getObjIdClass()));
        identity.setObjIdIdentity(aclObject.getObjIdIdentity());
        identity.setOwner(toAclSid(aclObject.getOwner()));
        identity.setParentObject(toObjectIdentity(aclObject.getParentObject()));

        return identity;
    }

    private static List<IAclEntry> toAclEntryList(List<AclEntryImpl> resultList, AclObjectIdentity identity) {
        List<IAclEntry> result = new ArrayList<IAclEntry>();
        if(resultList!=null) {
            for(AclEntryImpl aclObject:resultList) {
                result.add(toAclEntry(aclObject,identity));
            }
        }
        return result;
    }

    public static IAclEntry toAclEntry(AclEntryImpl aclObject, AclObjectIdentity identity) {
        AclEntry entry = new AclEntry();
        entry.setAceOrder(aclObject.getAceOrder());
        entry.setAclObjectIdentity(identity);
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

    public static AclObjectIdentityImpl toAclObjectIdentityImpl(IAclObjectIdentity aclObject,boolean resolveEntry) {
        if(aclObject==null) return null;
        AclObjectIdentityImpl identity = new AclObjectIdentityImpl();
        identity.setId(aclObject.getId());
        if(resolveEntry) {
            identity.setAclEntries(toAclEntryImplList(aclObject.getAclEntries()));
        }
        identity.setEntriesInheriting(aclObject.getEntriesInheriting());
        identity.setObjIdClass(toAclClassImpl(aclObject.getObjIdClass()));
        identity.setObjIdIdentity(aclObject.getObjIdIdentity());
        identity.setOwner(toAclSidImpl(aclObject.getOwner()));
        identity.setParentObject(toAclObjectIdentityImpl(aclObject.getParentObject(),resolveEntry));
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
        AclSidImpl sid = new AclSidImpl();
        sid.setId(sid2.getId());
        sid.setPrincipal(sid2.getPrincipal());
        sid.setSid(sid2.getSid());
        return sid;
    }

    public static IAclSid toAclSid(AclSidImpl sid2) {
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
        AclClassImpl aclClass = new AclClassImpl();
        aclClass.setClazz(clazz.getClazz());
        aclClass.setId(clazz.getId());
        return aclClass;
    }

    public static IAclClass toAclClass(AclClassImpl clazz) {
        AclClass aclClass = new AclClass();
        aclClass.setClazz(clazz.getClazz());
        aclClass.setId(clazz.getId());
        return aclClass;
    }

    public static AclEntryImpl toAclEntryImpl(IAclEntry aclObject) {
        AclEntryImpl entry = new AclEntryImpl();
        entry.setAceOrder(aclObject.getAceOrder());
        entry.setAclObjectIdentity(toAclObjectIdentityImpl(aclObject.getAclObjectIdentity(),false));
        entry.setAuditFailure(aclObject.getAuditFailure());
        entry.setAuditSuccess(aclObject.getAuditSuccess());
        entry.setGranting(aclObject.getGranting());
        entry.setId(aclObject.getId());
        entry.setMask(aclObject.getMask());
        entry.setSid(toAclSidImpl(aclObject.getSid()));
        return entry;
    }



    public static List<AclInfo> toAclInfoList(List<Object[]> result) {
        // TODO Auto-generated method stub
        List<AclInfo> list = new ArrayList<AclInfo>();
        for(Object[] acl:result) {
            AclInfo info = new AclInfo();
            info.setObjectIdIdentity((Long) acl[0]);
            info.setAceOrder((Integer) acl[1]);
            info.setAclId((Long) acl[2]);
            info.setParentObject((Long) acl[3]);
            info.setEntriesInheriting((Boolean) acl[4]);
            info.setAceId((Long) acl[5]);
            info.setMask((Integer) acl[6]);
            info.setGranting((Boolean) acl[7]);
            info.setAuditSuccess((Boolean) acl[8]);
            info.setAuditFailure((Boolean) acl[9]);
            info.setAcePrincipal((Boolean) acl[10]);
            info.setAceSid((String) acl[11]);
            info.setAclPrincipal((Boolean) acl[12]);
            info.setAclSid((String) acl[13]);
            info.setClazz((String) acl[14]);

            list.add(info);
        }
        return list;
    }

    public static List<String> toUsernameList(List<UserImpl> resultList) {
        // TODO Auto-generated method stub
        List<String> users = new ArrayList<String>();
        for(UserImpl usr:resultList) {
            users.add(usr.getUsername());
        }
        return users;
    }

    public static List<String> toRoleNameList(List<RoleImpl> resultList) {
        // TODO Auto-generated method stub
        List<String> users = new ArrayList<String>();
        for(RoleImpl usr:resultList) {
            users.add(usr.getName());
        }
        return users;
    }

    public static RememberMeTokenImpl toRemberMeTokenImpl(PersistentRememberMeToken token) {
        // TODO Auto-generated method stub
        RememberMeTokenImpl token2 = new RememberMeTokenImpl();
        token2.setDate(token.getDate());
        token2.setSeries(token.getSeries());
        token2.setTokenValue(token.getTokenValue());
        
        return token2;
    }

    public static PersistentRememberMeToken toPersistentRememberMeToken(RememberMeTokenImpl token) {
        if(token==null) return null;
        return new PersistentRememberMeToken(token.getUser()==null?null:token.getUser().getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
    }

}
