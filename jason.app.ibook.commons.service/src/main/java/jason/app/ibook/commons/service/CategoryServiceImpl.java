package jason.app.ibook.commons.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.Category;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ICategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;

	@Override
	public List<ICategory> listJobTypes() {
		// TODO Auto-generated method stub
		return categoryDao.findByParent(CategoryType.JOB_TYPE, null);
	}

	@Override
	public List<ICategory> listEducationLevels() {
		// TODO Auto-generated method stub
		return categoryDao.findByParent(CategoryType.EDUCATION_LEVEL, null);

	}

	@Override
	public List<ICategory> findFeatureByPattern(String pattern) {
		// TODO Auto-generated method stub
        return categoryDao.findByPattern(CategoryType.FEATURE,pattern);

	}

	@Override
	public List<ICategory> getJobIndustryStructure() {
		// TODO Auto-generated method stub
        List<ICategory> categories = new ArrayList<ICategory>();

        categories = categoryDao.findByParent(CategoryType.JOB_INDUSTRY,null);
        for (ICategory category : categories) {
            List<ICategory> children = categoryDao.findByParent(CategoryType.JOB_INDUSTRY,category.getId());
            if (children.size() > 0) {
                ((Category) category).setChildren(children);
                ((Category) category).setLeaf(false);
            } else {
                ((Category) category).setLeaf(true);
            }
        }
        return categories;
	}

	@Override
	public List<ICategory> listExperiences() {
		// TODO Auto-generated method stub
		return categoryDao.findByParent(CategoryType.EXPERIENCE, null);
	}

	@Override
	public List<ICategory> listCompanyTypes() {
		// TODO Auto-generated method stub
		return categoryDao.findByParent(CategoryType.COMPANY_TYPE, null);
	}

}
