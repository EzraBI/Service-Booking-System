package net.salon.booking.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.organization.organization_id = ?1")
	List<User> findByUsersByOrg();


	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User getByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?1")
	public User findByName(String firstName, String lastName);

	@Query("SELECT u FROM User u WHERE u.password = ?1")
	public User findByPassword(String password);



	@Query("SELECT u FROM User u WHERE u.organization = ?1")
	List<User> findAllUserWithOrganization(int organization_id);

	@Query("SELECT u FROM User u WHERE u.email IS NOT NULL AND u.password IS NOT NULL AND u.phone IS NOT NULL AND u.organization.organization_id = ?1")
	List<User> findAllUsersByOrganization(int organization_id);


	@Query("SELECT COUNT(u.id) FROM User u")
	int numberOfUsers();

    @Query("SELECT u FROM User u WHERE u.id = ?1")
	User findByOwnerId(Long owner_id);

//	@Query("SELECT u FROM Meetings u WHERE u.meeting_id = ?1 AND U.owner_id = ?1")
//	User findByOwnerEmail(Long owner_id);

/**The findByEmail() method will be used to check a user’s email when he starts to use the forgot password
 * function.And the findByResetPasswordToken() method will be used to VALIDATE the TOKEN when the user
 * clicks the change password link in email.**/

User findByResetPasswordToken(String token);

	User findBySetPasswordToken(String tokens);






	
}
