package net.salon.booking.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /** READ or LIST */
    public List<Category> listOrganization(){
        return categoryRepository.findAll();


    }
    /** COUNT */
    public int noOfOrg(){
        return categoryRepository.numberOfCategory();

    }
    /** CREATE */
    public void saveCategory(Category category){
        Optional<Category> findByOrganization_name = Optional.ofNullable(categoryRepository.findByCategory_Name(category.getCategory_name()));
        if (findByOrganization_name.isPresent()){
            throw new IllegalStateException("Organization with the same name already exists!");
        }
        categoryRepository.save(category);
    }
    /** UPDATE */
    public Category updateOrganization(Integer organization_id){
        return categoryRepository.findById(organization_id).get();
    }
    /** DELETE */
    public void deleteOrganization(Integer organization_id){
        categoryRepository.deleteById(organization_id);
    }

}
