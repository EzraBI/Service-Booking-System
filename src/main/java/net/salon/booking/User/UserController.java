package net.salon.booking.User;

import net.salon.booking.Category.Category;
import net.salon.booking.Category.CategoryService;
import net.salon.booking.Role.Role;
import net.salon.booking.Role.RoleRepository;
import net.salon.booking.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepo;



	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register") //ADD USER
	public String showRegistrationForm(Model model) {
//		String email = loggedUser.getUsername();
//		User user = service.getUserByEmail(email);


//		int organizationId = user.getOrganization().getOrganizationId();

		model.addAttribute("user", new User());

		List<String> listDepartment = Arrays.asList("Finance", "ICT", "Human Resource", "Accounting and Finance","Purchasing","Marketing");
		model.addAttribute("listDepartment", listDepartment);

		List<Role> listRoles = service.listRoles();
		model.addAttribute("listRoles", listRoles);

		List<Category> listCategory = categoryService.listOrganization();
		model.addAttribute("listOrganization", listCategory);

		return "User/signup_form";
	}

	@PostMapping("/process_register") //SAVE USER
	public String processRegister(User user, Model model) {
		try {
			service.registerDefaultUser(user);

		}
		catch (IllegalStateException ex) {
			model.addAttribute("error", ex.getMessage());
			return "User/signup_form";
		}
		return "User/add_user";
	}


    /**READ or LIST**/
	@GetMapping("/ListUsers")    //LIST USERS
	public String listUsers(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);



		String email = loggedUser.getUsername();
		User user = service.getUserByEmail(email);

		userRepository.numberOfUsers();

		model.addAttribute("loggedUser", user);

		return "User/ListUsers";
	}

	/**READ or LIST**/
	@GetMapping("/listAllUsers")    //LIST USERS
	public String listAllUsers(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		List<User> listAllUsers = service.listAll();
		model.addAttribute("listAllUsers", listAllUsers);


		String email = loggedUser.getUsername();
		User user = service.getUserByEmail(email);

		userRepository.numberOfUsers();

		model.addAttribute("loggedUser", user);

		return "Admin/ListAllUsers";
	}


	//@PathVariable indicates that a method parameter should be bound to
	// a URI template variable - used to extract the value from the URI
	//ModelAndView enables a controller to return both model and view in a
	// single return value
	@GetMapping("/users/edit/{id}")   //EDIT USER
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		model.addAttribute("user", user);

		List<Role> listRoles = service.listRoles();
		model.addAttribute("listRoles", listRoles);

		List<Category> listCategory = categoryService.listOrganization();
		model.addAttribute("listOrganization", listCategory);


		List<String> listDepartment = Arrays.asList("Finance", "ICT", "Human Resource", "Accounting and Finance","Purchasing","Marketing");
		model.addAttribute("listDepartment", listDepartment);

		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "User/EditUsers";
	}

	@PostMapping("/users/save") //SAVE USER
	public String saveUser(User user) {
		service.save(user);


		return "Admin/admin";
	}

	@RequestMapping("/delete_user/{id}") //DELETE USER
	public String deleteUser(@PathVariable(name = "id") Long id) {
		userRepository.deleteById(id);
		return "Admin/admin";
	}


	@GetMapping("/admin")
	public String viewAdminPage(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		String email = loggedUser.getUsername();
		User user = service.getUserByEmail(email);



		int users = service.noOfUsers();

		int organizations = categoryService.noOfOrg();


		model.addAttribute("noOfUsers", users);

		model.addAttribute("noOfOrg", organizations);


		return "Admin/admin";
	}

	@GetMapping("/contact")
	public String viewContactPage(Model model) {

		return "User/contact";
	}


	/*Login*/
	@GetMapping(path = "/login")
	public String getLoginPage(){
		return "User/login";
	}

//	@GetMapping(path = "/profile")
//	public String getProfilePage(){
//		return "User/profile";
//	}

	@GetMapping(path = "/edit_profile")
	public String getUserProfile1(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
		String email = loggedUser.getUsername();
		User user = service.getUserByEmail(email);

		List<Category> listCategory = categoryService.listOrganization();
		model.addAttribute("listOrganization", listCategory);


		List<String> listDepartment = Arrays.asList("Finance", "ICT", "Human Resource", "Accounting and Finance","Purchasing","Marketing");
		model.addAttribute("listDepartment", listDepartment);

		List<Role> listRoles = service.listRoles();
		model.addAttribute("listRoles", listRoles);

		model.addAttribute("loggedUser", user);
		return "User/edit_profile";
	}


	@GetMapping(path = "/profile1")
	public String getUserProfile(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
		String email = loggedUser.getUsername();
		User user = service.getUserByEmail(email);

		List<Category> listCategory = categoryService.listOrganization();
		model.addAttribute("listOrganization", listCategory);


		List<String> listDepartment = Arrays.asList("Finance", "ICT", "Human Resource", "Accounting and Finance","Purchasing","Marketing");
		model.addAttribute("listDepartment", listDepartment);

		model.addAttribute("loggedUser", user);
		return "User/profile1";
	}
	@PostMapping("/profile_update")
	public String updateProfile(User user, RedirectAttributes redirectAttributes,
								@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		loggedUser.setEmail(user.getEmail());
		loggedUser.setPhone(user.getPhone());
		loggedUser.setOrganization(user.getOrganization());

		loggedUser.setPassword(user.getPassword());

		List<Category> listCategory = categoryService.listOrganization();
		model.addAttribute("listOrganization", listCategory);


		List<String> listDepartment = Arrays.asList("Finance", "ICT", "Human Resource", "Accounting and Finance","Purchasing","Marketing");
		model.addAttribute("listDepartment", listDepartment);

		model.addAttribute("loggedUser", user);

		service.save(user);

		return "redirect:/profile1";

	}


}


