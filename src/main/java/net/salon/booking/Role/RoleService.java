package net.salon.booking.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    /** CREATE */
    public void createRole(Role role){
        Optional<Role> findByRole = Optional.ofNullable(roleRepo.findByRole(role.getName()));
        if (findByRole.isPresent()){
            throw new IllegalStateException("Role with the same name already exists!");
        }
        roleRepo.save(role);
    }
    /** CREATE */
    public void saveRole(Role role){
        roleRepo.save(role);
    }

    /** READ or LIST */
    public List<Role> listRoles(){
        return roleRepo.findAll();
    }
}
