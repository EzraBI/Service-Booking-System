package net.salon.booking.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;



    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add_org")
    public String showCategoryAddForm(Model model) {
        model.addAttribute("categoryRepository", new Category());


        return "Category/add_org";
    }

    @PostMapping("/create_categoryRepository")
    public String OrgCreate(Category category, Model model) {
        try {
            categoryService.saveCategory(category);
        }
        catch (IllegalStateException ex) {
            model.addAttribute("error", ex.getMessage());
            return "Category/add_org";
        }
        return "Admin/admin";
    }
    @PostMapping( "/saveCategory")
    public  String saveOrg(Category category){
        categoryRepository.save(category);

        return "Admin/admin";
    }

    @GetMapping("/list_categoryRepository")
    public String viewOrgList(Model model) {
        List<Category> listOrg = categoryRepository.findAll();
        model.addAttribute("listOrg", listOrg);
        return "Category/list_categoryRepository";
    }

    @RequestMapping("/delete_org/{categoryRepository_id}")
    public String deleteOrg(@PathVariable(name = "categoryRepository_id") Integer categoryRepository_id) {
        categoryRepository.deleteById(categoryRepository_id);
        return "Category/list_categoryRepository";
    }

    @RequestMapping("/edit_org/{categoryRepository_id}")
    public ModelAndView ShowEditOrg(@PathVariable(name = "categoryRepository_id") Integer categoryRepository_id) {
        ModelAndView umv = new ModelAndView("categoryRepository/edit_org");
        Category category = categoryRepository.findByCategory_id(categoryRepository_id);
        umv.addObject("edit_org", category);


        return umv;

    }
}