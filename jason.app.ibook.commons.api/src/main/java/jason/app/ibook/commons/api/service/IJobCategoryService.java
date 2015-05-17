package jason.app.ibook.commons.api.service;

import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface IJobCategoryService {
    List<ICategory> findByParent(Long parent);

	ICategory findById(Long category1);
}
