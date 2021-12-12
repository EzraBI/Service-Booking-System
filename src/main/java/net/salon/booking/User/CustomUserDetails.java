package net.salon.booking.User;

import net.salon.booking.Organization.Organization;
import net.salon.booking.Role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}

	public void setFirstName(String firstName){
		this.user.setFirstName(firstName);
	}
	public void setLastName(String lastName){
		this.user.setLastName(lastName);
	}

	public String getEmail(){
		return user.getEmail();
	}
	public void setEmail(String email){
		this.user.setEmail(email);
	}

	public String getPhone(){
		return user.getPhone();
	}
	public void setPhone(String phone){
		this.user.setPhone(phone);
	}


	public String getOrganization(){
		return user.getOrganization().getOrganization_name();
	}
	public void setOrganization(Organization organization){
		this.user.setOrganization(organization);
	}



	public String getGender(){
		return user.getGender();
	}
	public void setGender(String gender){
		this.user.setGender(gender);
	}
//	public String getPassword(){
//		return user.getPassword();
//	}
	public void setPassword(String password){
		this.user.setPassword(password);
	}

//	public boolean hasRole(String role_name){
//		return user.hasRole(role_name);
//	}

}
