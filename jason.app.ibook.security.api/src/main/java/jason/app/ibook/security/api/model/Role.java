package jason.app.ibook.security.api.model;

public class Role implements IRole {
    private String name;
    private String label;
    public void setLabel(String label) {
        this.label = label;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return label;
    }

}
