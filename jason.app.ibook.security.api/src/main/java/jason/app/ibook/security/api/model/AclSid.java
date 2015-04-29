package jason.app.ibook.security.api.model;


public class AclSid implements IAclSid{

    /**
	 * 
	 */
	private static final long serialVersionUID = -261084439425209959L;

	private Long id;

    private Boolean principal;

    private String sid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }   
           
}
