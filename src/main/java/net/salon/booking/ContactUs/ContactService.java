package net.salon.booking.ContactUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactService {
    @Autowired
    private ContactUsRepository contactUsRepository;

    /** LIST or READ **/
    public List<ContactUs> contactUsList(){
        return contactUsRepository.findAll();
    }

    /** CREATE **/
    public void saveContacts(ContactUs contactUs){
        contactUsRepository.save(contactUs);
    }

    public void saveContact(ContactUs contactUs){
        contactUsRepository.save(contactUs);
    }

    /** UPDATE **/
    public void updateContacts(ContactUs contactUs){
        contactUsRepository.findByContact_id(contactUs.getContact_id());
    }

    /** DELETE **/
    public void deleteContacts(ContactUs contactUs){
        contactUsRepository.findByContact_id(contactUs.getContact_id());
    }

}
