package jason.app.ibook.commons.api.dao;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface ICategoryDao {

    List<ICategory> findByParent(CategoryType location, Long parent);

    ICategory create(CategoryType type,Integer subType, String name, Long parent);
        
    List<ICategory> findByPattern(CategoryType location, String pattern);

	ICategory findById(Long id);

	ICategory create(CategoryType type, Integer subType, String name,
			String code, Long parent);
}
