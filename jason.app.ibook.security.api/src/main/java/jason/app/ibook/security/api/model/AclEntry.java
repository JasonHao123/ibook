package jason.app.ibook.security.api.model;


public class AclEntry implements IAclEntry{

    private Long id;

    private IAclObjectIdentity aclObjectIdentity;

    private Integer aceOrder;

    private IAclSid sid;

    private Integer mask;

    private Boolean granting;
    
    private Boolean auditSuccess;

    private Boolean auditFailure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IAclObjectIdentity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public void setAclObjectIdentity(IAclObjectIdentity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
    }

    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    public IAclSid getSid() {
        return sid;
    }

    public void setSid(IAclSid sid) {
        this.sid = sid;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

}
