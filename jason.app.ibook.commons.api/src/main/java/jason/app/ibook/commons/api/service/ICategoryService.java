package jason.app.ibook.commons.api.service;

import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface ICategoryService {
	public List<ICategory> listJobTypes();
	public List<ICategory> listEducationLevels();
	public List<ICategory> findFeatureByPattern(String pattern);
}
