package net.salon.booking.ContactUs;

import lombok.*;
import org.cyberneko.html.filters.Identity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "ContactUs")
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contact_id;

    @Column(name = "contacts_name", length = 50)
    private String contacts_name;

    @Column(name = "contacts_phone", length = 50)
    private String contacts_phone;

    @Column(name = "contacts_email", length = 50)
    private String contacts_email;

    @Column(name = "message", length = 250)
    private String message;

//    @Column(name = "subscribers_email",nullable = true, length = 50)
//    private String subscribers_email;


    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }

    public String getContacts_email() {
        return contacts_email;
    }

    public void setContacts_email(String contacts_email) {
        this.contacts_email = contacts_email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactUs{" +
                "contact_id=" + contact_id +
                ", contacts_name='" + contacts_name + '\'' +
                ", contacts_phone='" + contacts_phone + '\'' +
                ", contacts_email='" + contacts_email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
