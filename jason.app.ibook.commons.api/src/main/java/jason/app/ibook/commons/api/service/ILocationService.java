package jason.app.ibook.commons.api.service;

import jason.app.ibook.commons.api.model.ICategory;

import java.util.List;

public interface ILocationService {

    ICategory createCountry(String name);

    List<ICategory> findByParent(Long parent);

    ICategory createState(String name, Long parent);

    ICategory createCity(String name, Long parent);

    ICategory createDistrict(String name, Long parent);

}
