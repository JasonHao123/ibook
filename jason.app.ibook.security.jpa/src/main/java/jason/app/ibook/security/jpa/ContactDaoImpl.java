package jason.app.ibook.security.jpa;

import jason.app.ibook.api.dao.IContactDao;
import jason.app.ibook.api.model.IContact;
import jason.app.ibook.security.jpa.entity.ContactImpl;
import jason.app.ibook.security.jpa.util.BeanUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContactDaoImpl implements IContactDao {
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }
    @Override
    @Transactional
    public IContact create(IContact contact) {
        // TODO Auto-generated method stub
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);
         em.persist(contactImpl);
         em.flush();
         return BeanUtil.toContact(contactImpl);
    }

    @Override
    @Transactional
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
    @Transactional
    public void update(IContact contact) {
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);

        em.merge(contactImpl);
    }

    @Override
    @Transactional
    public void delete(IContact contact) {
        // TODO Auto-generated method stub
        ContactImpl contactImpl = BeanUtil.toContactImpl(contact);

        em.remove(contactImpl);
    }

}
