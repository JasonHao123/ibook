package jason.app.ibook.web.controller.job.model;

public class JobForm {
	private String title;
	private Long company;
	private Long department;
	private Long category1;
	private Long category2;
	private Long workType;
	private Integer numberOfVacancy;
	private String[] location;
	private String description;
	private String[] feature;
	private String educationLevel;
	private Integer minExperience;
	private Integer maxExperience;
	private String[] requiredSkill;
	private String[] desiredSkill;
	
	private Integer minSalary;
	private Integer maxSalary;

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Integer getNumberOfVacancy() {
		return numberOfVacancy;
	}

	public void setNumberOfVacancy(Integer numberOfVacancy) {
		this.numberOfVacancy = numberOfVacancy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

	public Long getCategory1() {
		return category1;
	}

	public void setCategory1(Long category1) {
		this.category1 = category1;
	}

	public Long getCategory2() {
		return category2;
	}

	public void setCategory2(Long category2) {
		this.category2 = category2;
	}

	public Long getWorkType() {
		return workType;
	}

	public void setWorkType(Long workType) {
		this.workType = workType;
	}
	
}
