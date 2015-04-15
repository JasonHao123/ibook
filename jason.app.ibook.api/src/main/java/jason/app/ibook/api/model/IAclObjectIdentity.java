package jason.app.ibook.api.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.acls.model.ObjectIdentity;

public interface IAclObjectIdentity extends ObjectIdentity{

    public List<IAclEntry> getAclEntries() ;

    public List<IAclEntry> getEntries() ;

   
    public Long getId();
    public IAclClass getObjIdClass() ;

   
    public Long getObjIdIdentity() ;

  
    public IAclObjectIdentity getParentObject();


    public IAclSid getOwner() ;


    public Boolean getEntriesInheriting();


}
