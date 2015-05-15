package jason.app.ibook.commons.api.service;

import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface IFeatureService {
    List<ICategory> findByPattern(String pattern);
}
