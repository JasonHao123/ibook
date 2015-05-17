package jason.app.ibook.job.api.model;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Job {
    @Field
	private Long id;
	 @Field
	private String title;
	@Field
	private String publisher;
	private Long companyId;

	private String companyName;
	
	private Long departmentId;
	private String departmentName;
	
	@Field
	private String description;


	private Long categoryId;
	@Field("categoryName")
	private String categoryName;


	private Long subCategoryId;
	@Field("subCategoryName")
	private String subCategoryName;

	private Long workTypeId;
	private String workTypeName;
	@Field("headCount")
	private Integer numberOfVacancy;
	@Field
	private String[] locations;
	@Field
    private String[] features;
    
    private Long educationLevelId;
    private String educationLevelName;
    
    private Integer minExperience;
    private Integer maxExperience;
    @Field
    private String[] requiredSkills;
    @Field
    private String[] desiredSkills;

    public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Long getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(Long educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	public String[] getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String[] requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String[] getDesiredSkills() {
		return desiredSkills;
	}

	public void setDesiredSkills(String[] desiredSkills) {
		this.desiredSkills = desiredSkills;
	}


   
}
