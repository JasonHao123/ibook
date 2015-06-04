package jason.app.ibook.job.craweller.analyzer;

public class Item {
    private String id;
    private String name;
    private String parent;
    private int type;
    private int subType;
    private String code;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getSubType() {
        return subType;
    }
    public void setSubType(int subType) {
        this.subType = subType;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
}
