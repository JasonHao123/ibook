package jason.app.ibook.filesystem.jpa;

import jason.app.ibook.filesystem.api.dao.IContactDao;
import jason.app.ibook.filesystem.api.model.IContact;
import jason.app.ibook.filesystem.jpa.entity.ContactImpl;
import jason.app.ibook.filesystem.jpa.util.BeanUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ContactDaoImpl implements IContactDao {
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
    @Override

    public IContact create(IContact contact) {
        // TODO Auto-generated method stub
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);
         em.persist(contactImpl);
         em.flush();
         return BeanUtil.toContact(contactImpl);
    }

    @Override

    public void delete(Long contactId) {
        // TODO Auto-generated method stub
       ContactImpl contact =  em.find(ContactImpl.class, contactId);
        em.remove(contact);
    }

    @Override
    public List<IContact> findAll() {
        Query query = em.createQuery("select u from ContactImpl u");
                                                               
        return BeanUtil.toContactList(query.getResultList());
    }

    @Override
    public IContact getById(Long id) {
        
        return BeanUtil.toContact(em.find(ContactImpl.class, id));
    }

    @Override

    public void update(IContact contact) {
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);

        em.merge(contactImpl);
    }

    @Override

    public void delete(IContact contact) {
        // TODO Auto-generated method stub
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);

        em.remove(contactImpl);
    }

}
