package jason.app.ibook.security.api.model;

public interface IAclEntry {

    public Long getId() ;

    public IAclObjectIdentity getAclObjectIdentity() ;

    public Integer getAceOrder() ;

    public IAclSid getSid();

    public Integer getMask() ;

    public Boolean getGranting() ;

    public Boolean getAuditSuccess() ;

    public Boolean getAuditFailure();
}
