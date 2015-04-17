package jason.app.ibook.security.api.model;

import java.io.Serializable;
import java.util.List;

public class AclObjectIdentity implements IAclObjectIdentity{

    private Long id;

    private IAclClass objIdClass;

    private Long objIdIdentity;

    private IAclObjectIdentity parentObject;

    private IAclSid owner;

    private Boolean entriesInheriting;

    private List<IAclEntry> aclEntries;

    public List<IAclEntry> getAclEntries() {
        return aclEntries;
    }

    public void setAclEntries(List<IAclEntry> aclEntries) {
        this.aclEntries = aclEntries;
    }

    public List<IAclEntry> getEntries() {
        if(aclEntries!=null) {
            return aclEntries;
        }
        return null;
    }

    public void setEntries(List<IAclEntry> entries) {
        this.aclEntries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IAclClass getObjIdClass() {
        return objIdClass;
    }

    public void setObjIdClass(IAclClass objIdClass) {
        this.objIdClass = objIdClass;
    }

    public Long getObjIdIdentity() {
        return objIdIdentity;
    }

    public void setObjIdIdentity(Long objIdIdentity) {
        this.objIdIdentity = objIdIdentity;
    }

    public IAclObjectIdentity getParentObject() {
        return parentObject;
    }

    public void setParentObject(IAclObjectIdentity parentObject) {
        this.parentObject = parentObject;
    }

    public IAclSid getOwner() {
        return owner;
    }

    public void setOwner(IAclSid owner) {
        this.owner = owner;
    }

    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }

    @Override
    public Serializable getIdentifier() {
        
        return this.objIdIdentity;
    }

    @Override
    public String getType() {

        return objIdClass.getClazz();
    }

}
