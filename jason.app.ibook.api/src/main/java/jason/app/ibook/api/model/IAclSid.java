package jason.app.ibook.api.model;

import org.springframework.security.acls.model.Sid;

public interface IAclSid extends Sid{
    public Long getId() ;


    public Boolean getPrincipal() ;


    public String getSid() ;

}
