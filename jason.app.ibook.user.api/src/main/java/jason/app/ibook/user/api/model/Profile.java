package jason.app.ibook.user.api.model;

import org.apache.solr.client.solrj.beans.Field;

public class Profile {
    @Field
    private String id;
    @Field
    private String type;

    private String username;
    @Field
    private String realname;
    @Field
    private String[] skill;
    @Field
    private String description;
    @Field
    private String[] industry;
    public String[] getSkill() {
        return skill;
    }
    public void setSkill(String[] skill) {
        this.skill = skill;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String[] getIndustry() {
        return industry;
    }
    public void setIndustry(String[] industry) {
        this.industry = industry;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
}
