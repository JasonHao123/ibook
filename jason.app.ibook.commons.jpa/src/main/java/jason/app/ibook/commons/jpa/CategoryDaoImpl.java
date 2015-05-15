package jason.app.ibook.commons.jpa;

import jason.app.ibook.commons.api.constant.CategoryType;
import jason.app.ibook.commons.api.dao.ICategoryDao;
import jason.app.ibook.commons.api.model.Category;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.jpa.entity.CategoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CategoryDaoImpl implements ICategoryDao {
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }

    @Override
    public ICategory create(CategoryType type, Integer subType,String name,Long parent) {
        // TODO Auto-generated method stub
        CategoryImpl category = new CategoryImpl();
        category.setName(name);
        category.setType(type);
        category.setSubType(subType);
        if(parent!=null) {
            category.setParent(em.find(CategoryImpl.class, parent));
        }
         em.persist(category);
         em.flush();
        Category c = new Category();
        c.setName(name);
        c.setId(category.getId());
        c.setType(type);
        c.setSubType(subType);
        return c;
    }

    @Override
    public List<ICategory> findByParent(CategoryType type,Long parent) {
        // TODO Auto-generated method stub
        Query query = em.createQuery("select l from CategoryImpl l where l.type = :type and l.parent.id = :id");
        query.setParameter("type", type);
        query.setParameter("id", parent);
        List<CategoryImpl> locations = query.getResultList();
        List<ICategory> result = new ArrayList<ICategory>();
        for(CategoryImpl location:locations) {
            result.add(createLocation(location));
        }
        return result;
    }

    private ICategory createLocation(CategoryImpl category) {
        // TODO Auto-generated method stub
        Category cate = new Category();
        cate.setId(category.getId());
        cate.setName(category.getName());
        if(category.getParent()!=null) {
            cate.setParent(createLocation(category.getParent()));
        }
        cate.setType(category.getType());
        cate.setSubType(category.getSubType());
        return cate;
    }

    @Override
    public List<ICategory> findByPattern(CategoryType type, String pattern) {

            // TODO Auto-generated method stub
            Query query = em.createQuery("select l from CategoryImpl l where l.type = :type and lower(l.name) like :pattern");
            query.setParameter("type", type);
            query.setParameter("pattern", pattern+"%".toLowerCase());
            List<CategoryImpl> locations = query.getResultList();
            List<ICategory> result = new ArrayList<ICategory>();
            for(CategoryImpl location:locations) {
                result.add(createLocation(location));
            }
            return result;

    }
}
