package jason.app.ibook.filesystem.jpa.util;

import jason.app.ibook.filesystem.api.model.Contact;
import jason.app.ibook.filesystem.api.model.IContact;
import jason.app.ibook.filesystem.jpa.entity.ContactImpl;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {
    public static ContactImpl toContactImpl(IContact contact) {
        // TODO Auto-generated method stub
        ContactImpl contactImpl = new ContactImpl();
        contactImpl.setId(contact.getId());
        contactImpl.setEmail(contact.getEmail());
        contactImpl.setName(contact.getName());
        return contactImpl;
    }

    public static IContact toContact(ContactImpl contact) {
        Contact contactImpl = new Contact();
        contactImpl.setId(contact.getId());
        contactImpl.setEmail(contact.getEmail());
        contactImpl.setName(contact.getName());
        return contactImpl;
    }

    public static List<IContact> toContactList(List<ContactImpl> resultList) {
        List<IContact> result = new ArrayList<IContact>();
        if(resultList!=null) {
            for(ContactImpl aclObject:resultList) {
                result.add(toContact(aclObject));
            }
        }
        return result;
    }
}
