package net.salon.booking.ContactUs;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactUsRepository extends JpaRepository <ContactUs, Integer> {
    @Query("SELECT c FROM ContactUs  c WHERE c.contact_id = ?1")
    ContactUs findByContact_id(Integer contact_id);

}
