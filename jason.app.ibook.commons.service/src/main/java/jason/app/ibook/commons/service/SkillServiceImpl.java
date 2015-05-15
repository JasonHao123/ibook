package jason.app.ibook.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ISkillService;

@Service("skillService")
public class SkillServiceImpl implements ISkillService {
    @Autowired
    private ICategoryDao categoryDao;

	@Override
	public List<ICategory> findByPattern(String pattern) {
		// TODO Auto-generated method stub
		return categoryDao.findByPattern(CategoryType.SKILL, pattern);
	}

}
