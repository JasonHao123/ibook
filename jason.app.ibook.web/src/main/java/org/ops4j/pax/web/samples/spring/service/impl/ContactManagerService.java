package org.ops4j.pax.web.samples.spring.service.impl;

import jason.app.ibook.api.dao.IContactDao;
import jason.app.ibook.api.model.Contact;
import jason.app.ibook.api.model.IContact;
import jason.app.ibook.api.service.ISecurityService;

import java.util.Arrays;
import java.util.List;

import org.ops4j.pax.web.samples.spring.service.ContactManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactManagerService implements ContactManager {
    
    @Autowired
    private IContactDao contactDao;
    
    @Autowired
    private ISecurityService securityService;
    
    @Autowired
    private MutableAclService mutableAclService;

    @Override
    public void addPermission(IContact contact, Sid recipient, Permission permission) {
        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
                                                                                     contact.getId()));
        acl.insertAce(acl.getEntries().size(), permission, recipient, true);

        mutableAclService.updateAcl(acl);
        
    }

    @Override
    public void deletePermission(IContact contact, Sid recipient, Permission permission) {
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
    public void create(IContact contact) {
        // TODO Auto-generated method stub
        contact = contactDao.create(contact);
        securityService.createUser(contact.getName(), contact.getEmail(), Arrays.asList(new String[]{"user","supervisor"}));
        // Create acl_object_identity rows (and also acl_class rows as needed

        final ObjectIdentity objectIdentity = new ObjectIdentityImpl(IContact.class, contact.getId());
        mutableAclService.createAcl(objectIdentity);
//
//        AclImpl acl = (AclImpl) mutableAclService.readAclById(new ObjectIdentityImpl(Contact.class,
//                                                                                     contact.getId()));
//        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, new PrincipalSid(SecurityContextHolder.getContext().getAuthentication().getName()), true);
//
//        mutableAclService.updateAcl(acl);
 
    }

    @Override
    public void delete(IContact contact) {
        // TODO Auto-generated method stub
        contactDao.delete(contact.getId());
    }

    @Override
    public List<IContact> getAll() {
        
        return contactDao.findAll();
    }

    @Override

    public List<String> getAllRecipients() {
        return contactDao.findAllPrincipals();
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
