package jason.app.ibook.job.api.model;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Job {
    @Field
	private String id;
	 @Field
	private String title;
	@Field
	private String publisher;
	
    @Field	
	private String companyId;
    @Field
	private String companyName;
    @Field	
	private String departmentId;
    @Field
	private String departmentName;
	
	@Field
	private String description;

    @Field
	private String categoryId;
	@Field
	private String categoryName;

	@Field
	private String subCategoryId;
	@Field
	private String subCategoryName;
	@Field
	private String workTypeId;
	@Field
	private String workTypeName;
	@Field("headCount")
	private Integer numberOfVacancy;
	@Field
	private String[] location;
	@Field
    private String[] feature;
	@Field
    private String educationLevelId;
	@Field
    private String educationLevelName;
	
	@Field
    private String experienceId;
	@Field
    private String experienceName;

    @Field
    private String[] requiredSkill;
    @Field
    private String[] desiredSkill;
    
    @Field
    private Date publishDate;

    public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(String educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public Integer getNumberOfVacancy() {
        return numberOfVacancy;
    }

    public void setNumberOfVacancy(Integer numberOfVacancy) {
        this.numberOfVacancy = numberOfVacancy;
    }


    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

	public String[] getLocation() {
		return location;
	}

	public void setLocation(String[] location) {
		this.location = location;
	}

	public String[] getFeature() {
		return feature;
	}

	public void setFeature(String[] feature) {
		this.feature = feature;
	}

	public String[] getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(String[] requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public String[] getDesiredSkill() {
		return desiredSkill;
	}

	public void setDesiredSkill(String[] desiredSkill) {
		this.desiredSkill = desiredSkill;
	}

	public String getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(String experienceId) {
		this.experienceId = experienceId;
	}

	public String getExperienceName() {
		return experienceName;
	}

	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}
