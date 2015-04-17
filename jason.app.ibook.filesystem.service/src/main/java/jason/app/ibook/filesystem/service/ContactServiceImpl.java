package jason.app.ibook.filesystem.service;

import jason.app.ibook.filesystem.api.dao.IContactDao;
import jason.app.ibook.filesystem.api.model.Contact;
import jason.app.ibook.filesystem.api.model.IContact;
import jason.app.ibook.filesystem.api.service.IContactService;
import jason.app.ibook.security.api.model.IUser;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.List;

import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;


public class ContactServiceImpl implements IContactService {
    private PermissionFactory factory = new DefaultPermissionFactory();

    private IContactDao contactDao;
    
    private ISecurityService securityService;
    
    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

    public IContactDao getContactDao() {
        return contactDao;
    }

    public void setContactDao(IContactDao contactDao) {
        this.contactDao = contactDao;
    }


    private MutableAclService mutableAclService;

    public MutableAclService getMutableAclService() {
        return mutableAclService;
    }

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Override
    @Transactional
    public void addPermission(IContact contact, String recipient, int permission) {
        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
     //   acl.insertAce(acl.getEntries().size(), factory.buildFromMask(permission), new PrincipalSid(recipient), true);
        securityService.insertAce(acl,factory.buildFromMask(permission),new PrincipalSid(recipient),true);

        mutableAclService.updateAcl(acl);
        
    }

    @Override
    @Transactional
    public void deletePermission(IContact contact, String recipient, int permission) {
        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
        int index = -1;
        for(int i=0;i<acl.getEntries().size();i++) {
            if(acl.getEntries().get(i).getSid().equals(recipient)) {
                index = i;
                break;
            }
        }
        if(index>0) {
            acl.deleteAce(index);

            mutableAclService.updateAcl(acl);
        }
        
        

    }

    @Override
    @Transactional
    public void create(IContact contact,String user) {
        // TODO Auto-generated method stub
        contact = contactDao.create(contact);
        // Create acl_object_identity rows (and also acl_class rows as needed

        final ObjectIdentity objectIdentity = new ObjectIdentityImpl(Contact.class, contact.getId());
        mutableAclService.createAcl(objectIdentity);

        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
        securityService.insertAce(acl,BasePermission.ADMINISTRATION,new PrincipalSid(user),true);
       // acl.insertAce(acl.getEntries().size(),BasePermission.ADMINISTRATION , new PrincipalSid(SecurityContextHolder.getContext().getAuthentication().getName()), true);

        mutableAclService.updateAcl(acl);
 
    }

    @Override
    @Transactional
    public void delete(IContact contact) {
        // TODO Auto-generated method stub
        contactDao.delete(contact.getId());
    }

    @Override
    public List<IContact> getAll() {
        
        return contactDao.findAll();
    }

    @Override
    public IContact getById(Long id) {
        // TODO Auto-generated method stub
        return contactDao.getById(id);
    }

    @Override
    public IContact getRandomContact() {
        // TODO Auto-generated method stub
        return null;
    }

}
