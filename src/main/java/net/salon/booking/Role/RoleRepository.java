package net.salon.booking.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("SELECT r FROM Role r WHERE r.id = ?1")
	public Role getById(Integer id);

	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findByRole(String name);

	Role findByName(String name);
}
