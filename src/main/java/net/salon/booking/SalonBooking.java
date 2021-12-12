package net.salon.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SalonBooking {

	public static void main(String[] args) {
		SpringApplication.run(SalonBooking.class, args);
	}

	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration{

	}

}





















//@Autowired
//	private RoleRepository roleRepository;
//
//	public void saveRole() {
//		Role user = new Role("USER");
//		Role admin = new Role("ADMIN");
//		Role super_admin= new Role("SUPER_ADMIN");
//
//		roleRepository.saveAll(List.of(user, admin, super_admin));
//
//		List<Role> listRoles = roleRepository.findAll();
//
////		assertThat(listRoles.size()).isEqualTo(3);
//	}
//
//	@Autowired
//	private UserRepository userRepository;
//
//	public void testAddRoleToNewUser() {
//		Role roleAdmin = roleRepository.findByName("Admin");
//
//		User user = new User();
//		user.setEmail("bianalyst77@gmail.com");
//		user.setPassword("bianalyst77@gmail.com");
//		user.setFirstName("Super");
//		user.setLastName("_Admin");
//		user.setPhone("+254701727389");
//		user.setGender("Male");
//		user.setDepartment("ICT");
//		user.setOrganization(user.getOrganization());
//		user.addRole(roleAdmin);
//
//		User savedUser = userRepository.save(user);
//
//	}



