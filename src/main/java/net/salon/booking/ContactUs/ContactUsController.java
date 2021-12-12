package net.salon.booking.ContactUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ContactUsController {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private ContactService contactService;


    @GetMapping("/add_contact")
    public String addContact(Model model) {
        model.addAttribute("contactUs", new ContactUs());

        return "User/contact";
    }

    @PostMapping("/save_contact") //SAVE USER
    public String saveContact(ContactUs contactUs, Model model) {
     contactService.saveContact(contactUs);

        return "index";
    }



    @GetMapping("/list_contacts")
    public String viewContactsList(Model model) {
        List<ContactUs> list_contacts = contactUsRepository.findAll();
        model.addAttribute("list_contacts", list_contacts);

        return "User/list_contacts";
    }

    @RequestMapping("/delete_contact/{contact_id}")
    public String deleteContact(@PathVariable(name = "contact_id") Integer contact_id) {
        contactUsRepository.deleteById(contact_id);

        return "redirect:list_contacts";
    }


}
