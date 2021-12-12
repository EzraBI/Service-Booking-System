package net.salon.booking.Category;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT o FROM Category o WHERE o.category_id = ?1")
    Category findByCategory_id(Integer organization_id);

    @Query ("SELECT COUNT (o.category_id) FROM Category o")
    int numberOfCategory();

    @Query("SELECT o FROM Organization o WHERE o.organization_name = ?1")
    Category findByCategory_Name(String category_name);




    //Organization getById(Integer organization_id);

}

