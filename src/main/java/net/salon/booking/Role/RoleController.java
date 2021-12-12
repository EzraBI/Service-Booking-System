package net.salon.booking.Role;


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
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/add_role")
    public String showRoleAddForm(Model model) { //ADD
        model.addAttribute("role", new Role());
        return "Role/add_role";
    }
    @PostMapping("/save_role")
    public String roleCreate(Role role) { //SAVE

        roleRepository.save(role);
        return "Role/listRoles";
    }

    @GetMapping("/listRoles")  //LIST
    public String viewRolesList(Model model){
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        return "Role/listRoles";
    }

    @RequestMapping("/edit_role/id}") //EDIT
    public ModelAndView ShowEditRole(@PathVariable(name = "id") Integer id) {
        ModelAndView umv = new ModelAndView("Role/edit_role");
        Role role = roleRepository.getById(id);
        umv.addObject("edit_roles", role);
        return umv;
    }
    @RequestMapping("/delete_role/{id}") //DELETE
    public String deleteEmployee(@PathVariable(name = "id") Integer id) {
        roleRepository.deleteById(id);
        return "Role/listRoles";
    }



}
