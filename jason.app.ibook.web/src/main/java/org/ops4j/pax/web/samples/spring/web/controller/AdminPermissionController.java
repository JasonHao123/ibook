package org.ops4j.pax.web.samples.spring.web.controller;

import jason.app.ibook.filesystem.api.model.IContact;
import jason.app.ibook.filesystem.api.service.IContactService;
import jason.app.ibook.security.api.service.ISecurityService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.ops4j.pax.web.samples.spring.web.model.AddPermission;
import org.ops4j.pax.web.samples.spring.web.validator.AddPermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
 * Web controller to handle <tt>Permission</tt> administration functions - adding and deleting
 * permissions for contacts.
 *
 * @author Luke Taylor
 * @since 3.0
 */
@Controller
@SessionAttributes("addPermission")
public final class AdminPermissionController implements MessageSourceAware{
    @Autowired
    private AclService aclService;
    @Autowired
    private IContactService contactManager;
    
    @Autowired
    private ISecurityService securityService;
    
    private MessageSourceAccessor messages;
    private final Validator addPermissionValidator = new AddPermissionValidator();
    private final PermissionFactory permissionFactory = new DefaultPermissionFactory();

    /**
     * Displays the permission admin page for a particular contact.
     */
    @RequestMapping(value="/adminPermission.htm", method=RequestMethod.GET)
    public ModelAndView displayAdminPage(@RequestParam("contactId") int contactId) {
        IContact contact = contactManager.getById(Long.valueOf(contactId));
        Acl acl = aclService.readAclById(new ObjectIdentityImpl(contact));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("contact", contact);
        model.put("acl", acl);

        return new ModelAndView("adminPermission", "model", model);
    }

    /**
     * Displays the "add permission" page for a contact.
     */
    @RequestMapping(value="/addPermission.htm", method=RequestMethod.GET)
    public ModelAndView displayAddPermissionPageForContact(@RequestParam("contactId") int contactId) {
        IContact contact = contactManager.getById(new Long(contactId));

        AddPermission addPermission = new AddPermission();
        addPermission.setContact(contact);

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("addPermission", addPermission);
        model.put("recipients", listRecipients());
        model.put("permissions", listPermissions());

        return new ModelAndView("addPermission", model);
    }

    @InitBinder("addPermission")
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("recipient", "permission");
    }

    /**
     * Handles submission of the "add permission" form.
     */
    @RequestMapping(value="/addPermission.htm", method=RequestMethod.POST)
    public String addPermission(AddPermission addPermission, BindingResult result, ModelMap model) {
        addPermissionValidator.validate(addPermission, result);

        if (result.hasErrors()) {
            model.put("recipients", listRecipients());
            model.put("permissions", listPermissions());

            return "addPermission";
        }

//        PrincipalSid sid = new PrincipalSid(addPermission.getRecipient());
//        Permission permission = permissionFactory.buildFromMask(addPermission.getPermission());

        try {
            contactManager.addPermission(addPermission.getContact(), addPermission.getRecipient(), addPermission.getPermission());
        } catch (DataAccessException existingPermission) {
            existingPermission.printStackTrace();
            result.rejectValue("recipient", "err.recipientExistsForContact", "Addition failure.");

            model.put("recipients", listRecipients());
            model.put("permissions", listPermissions());
            return "addPermission";
        }

        return "redirect:/index.htm";
    }

    /**
     * Deletes a permission
     */
    @RequestMapping(value="/deletePermission.htm")
    public ModelAndView deletePermission(
            @RequestParam("contactId") int contactId,
            @RequestParam("sid") String sid,
            @RequestParam("permission") int mask) {

        IContact contact = contactManager.getById(new Long(contactId));

        Sid sidObject = new PrincipalSid(sid);
        Permission permission = permissionFactory.buildFromMask(mask);

        contactManager.deletePermission(contact, sid, mask);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("contact", contact);
        model.put("sid", sidObject);
        model.put("permission", permission);

        return new ModelAndView("deletePermission", "model", model);
    }

    private Map<Integer, String> listPermissions() {
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(Integer.valueOf(BasePermission.ADMINISTRATION.getMask()), messages.getMessage("select.administer", "Administer"));
        map.put(Integer.valueOf(BasePermission.READ.getMask()), messages.getMessage("select.read", "Read"));
        map.put(Integer.valueOf(BasePermission.DELETE.getMask()), messages.getMessage("select.delete", "Delete"));

        return map;
    }

    private Map<String, String> listRecipients() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("", messages.getMessage("select.pleaseSelect", "-- please select --"));

        for (String recipient : securityService.getAllUsernames()) {
            map.put(recipient, recipient);
        }

        return map;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
