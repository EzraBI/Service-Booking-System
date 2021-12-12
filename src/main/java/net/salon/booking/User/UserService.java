package net.salon.booking.User;

import net.salon.booking.Organization.Organization;
import net.salon.booking.Organization.OrganizationRepository;
import net.salon.booking.Role.Role;
import net.salon.booking.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired PasswordEncoder passwordEncoder;

/**	CREATE */
	public void registerDefaultUser(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
//		encodePassword(user);
		Optional<User>userByEmail = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));
		if (userByEmail.isPresent()){
			throw new IllegalStateException("Account with the same email exists!");
		}
		userRepo.save(user);
	}


/**	LIST */
	public List<User> listAll() {
		return userRepo.findAll();
	}
	public List<User> ShowUsersByOrg(int organization_id){
		return userRepo.findAllUsersByOrganization(organization_id);
	}


	public List <User> listCoOwners(){
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}

	public List<Role> listRoles() {
		return roleRepo.findAll();
	}


	public Organization get(Integer organization_id) {
		return organizationRepository.findById(organization_id).get();
	}

	public List<Organization> listOrganizations() {
		return organizationRepository.findAll();
	}
	
	public void save(User user) {
		encodePassword(user);		
		userRepo.save(user);
	}
	public int noOfUsers(){
		return userRepo.numberOfUsers();
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}

	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public List<User> getUsersByOrganization(int organization_id) {
		return userRepo.findAllUserWithOrganization(organization_id);
	}

		public List<User> getAllUsersByOrganization(int organization_id) {
			return userRepo.findAllUsersByOrganization(organization_id);
		}


/** SETTING Token by checking users Email whether it exists, Setting token and SAVING user **/
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User user = userRepo.findByEmail(email);

		if (user != null){
			user.setResetPasswordToken(token);
			userRepo.save(user);
		}
		else {
			throw new UserNotFoundException("Could not find any user with email: " + email);

		}
	}
/** This method will be used by controller to check if a user belongs to a given token or not **/
	/** Find by password token **/

	public User getByResetPasswordToken(String token) {
		return userRepo.findByResetPasswordToken(token);
	}

	/** Update password then reset password token to null **/
	public void updatePassword(User user, String newPassword){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);

		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);

		userRepo.save(user);
	}


		/**SETTING PASSWORD**/


	public void updateSetPasswordToken(String tokens, String email) throws UserNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			user.setSetPasswordToken(tokens);
			userRepo.save(user);
		} else {
			throw new UserNotFoundException("Could not find any User with the email " + email);
		}
	}

	public User getBySetPasswordToken(String tokens) {
		return userRepo.findBySetPasswordToken(tokens);
	}

	/** Update password then reset password token to null **/
	public void updatePasswords(User user, String newPassword){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);

		user.setPassword(encodedPassword);
		user.setSetPasswordToken(null);

		userRepo.save(user);
	}

}
