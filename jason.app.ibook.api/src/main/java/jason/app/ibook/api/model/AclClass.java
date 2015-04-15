package jason.app.ibook.api.model;


public class AclClass implements IAclClass{

    private Long id;

    private String clazz;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClazz() {
        return clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
