package jason.app.ibook.commons.service;

import java.util.ArrayList;
import java.util.List;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.Category;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.IJobCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobCategoryService")
public class JobCategoryServiceImpl implements IJobCategoryService {
    @Autowired
    private ICategoryDao locationDao;

    @Override
    public List<ICategory> findByParent(Long parent) {
        // TODO Auto-generated method stub
        return locationDao.findByParent(CategoryType.JOB_CATEGORY, parent);
    }

    @Override
    public ICategory findById(Long id) {
        // TODO Auto-generated method stub
        return locationDao.findById(id);
    }

    @Override
    public List<ICategory> getJobCategoryStructure() {
        List<ICategory> categories = new ArrayList<ICategory>();

        categories = findByParent(null);
        for (ICategory category : categories) {
            List<ICategory> children = findByParent(category.getId());
            if (children.size() > 0) {
                ((Category) category).setChildren(children);
                ((Category) category).setLeaf(false);
            } else {
                ((Category) category).setLeaf(true);
            }
        }
        return categories;
    }
}
