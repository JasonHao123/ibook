package jason.app.ibook.commons.api.dao;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface ICategoryDao {

    List<ICategory> findByParent(CategoryType location, Long parent);

    ICategory create(CategoryType type,Integer subType, String name, Long parent);

}
