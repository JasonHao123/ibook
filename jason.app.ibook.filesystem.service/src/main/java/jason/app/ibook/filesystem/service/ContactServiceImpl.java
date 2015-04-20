package jason.app.ibook.filesystem.service;

import jason.app.ibook.filesystem.api.dao.IContactDao;
import jason.app.ibook.filesystem.api.model.Contact;
import jason.app.ibook.filesystem.api.model.IContact;
import jason.app.ibook.filesystem.api.service.IContactService;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.intercept.aspectj.aspect.AnnotationSecurityAspect;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("contactService")
public class ContactServiceImpl implements IContactService,ApplicationContextAware {
    private PermissionFactory factory = new DefaultPermissionFactory();

    @Autowired 
    private IContactDao contactDao;
    
    @Autowired
    private ISecurityService securityService;
    

    @Autowired
    private MutableAclService mutableAclService;

    private ApplicationContext context;
    
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


    public MutableAclService getMutableAclService() {
        return mutableAclService;
    }

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(#contact, admin)")
    public void addPermission(IContact contact, String recipient, int permission) {
        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
        System.out.println(AnnotationSecurityAspect.class.toString());
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
    @PreAuthorize("hasRole(supervisor)")
    public void create(IContact contact,String user) {
        // TODO Auto-generated method stub
        contact = contactDao.create(contact);
        // Create acl_object_identity rows (and also acl_class rows as needed

        final ObjectIdentity objectIdentity = new ObjectIdentityImpl(Contact.class, contact.getId());
        mutableAclService.createAcl(objectIdentity);

        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
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

        if(context!=null) {
        //   System.out.println(context.getBean(PrePostInvocationAttributeFactory.class));
        }
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.context =applicationContext;
    } 

}
