package jason.app.ibook.commons.service;

import java.util.List;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.IJobCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobCategoryService")
public class JobCategoryServiceImpl implements IJobCategoryService{
    @Autowired
    private ICategoryDao locationDao;

    @Override
    public List<ICategory> findByParent(Long parent) {
        // TODO Auto-generated method stub
        return locationDao.findByParent(CategoryType.JOB_CATEGORY, parent);
    }
}
