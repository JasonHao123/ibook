package jason.app.ibook.commons.service;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.constant.LocationType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ILocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("locationService")
public class LocationServiceImpl implements ILocationService {
    @Autowired
    private ICategoryDao locationDao;

    @Override
    @Transactional
    public ICategory createCountry(String name) {
        // TODO Auto-generated method stub
        return locationDao.create(CategoryType.LOCATION,LocationType.COUNTRY.ordinal(),name,null);
    }

    @Override
    public List<ICategory> findByParent(Long parent) {
        // TODO Auto-generated method stub
        return locationDao.findByParent(CategoryType.LOCATION,parent);
    }

    @Override
    public ICategory createState(String name, Long parent) {
        return locationDao.create(CategoryType.LOCATION,LocationType.STATE.ordinal(),name,parent);
    }

    @Override
    public ICategory createCity(String name, Long parent) {
        return locationDao.create(CategoryType.LOCATION,LocationType.CITY.ordinal(),name,parent);
    }

    @Override
    public ICategory createDistrict(String name, Long parent) {
        // TODO Auto-generated method stub
        return locationDao.create(CategoryType.LOCATION,LocationType.DISTRICT.ordinal(),name,parent);
    }
    
    
}
