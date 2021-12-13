package net.salon.booking.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User getByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?1")
	public User findByName(String firstName, String lastName);

	@Query("SELECT u FROM User u WHERE u.password = ?1")
	public User findByPassword(String password);


	@Query("SELECT COUNT(u.id) FROM User u")
	int numberOfUsers();


/**The findByEmail() method will be used to check a user’s email when he starts to use the forgot password
 * function.And the findByResetPasswordToken() method will be used to VALIDATE the TOKEN when the user
 * clicks the change password link in email.**/

User findByResetPasswordToken(String token);

	User findBySetPasswordToken(String tokens);






	
}
