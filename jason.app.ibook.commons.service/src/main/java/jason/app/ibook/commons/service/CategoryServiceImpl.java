package jason.app.ibook.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
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

}
