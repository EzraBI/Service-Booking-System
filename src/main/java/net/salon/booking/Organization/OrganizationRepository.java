package net.salon.booking.Organization;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    @Query("SELECT o FROM Organization o WHERE o.organization_id = ?1")
    Organization findByOrganization_id(Integer organization_id);

    @Query ("SELECT COUNT (o.organization_id) FROM Organization o")
    int numberOfOrganizations();

    @Query("SELECT o FROM Organization o WHERE o.organization_name = ?1")
    Organization findByOrganization_Name(String organization_name);




    //Organization getById(Integer organization_id);

}

