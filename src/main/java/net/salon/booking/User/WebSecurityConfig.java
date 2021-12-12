package net.salon.booking.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//@Configuration annotation indicates that a class declares one or more @Bean methods
// and may be processed by the Spring container to generate bean definitions and
// service requests for those beans at runtime.
//@Bean tells that a method to produces a bean to be managed by the Spring container
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}password").roles("USER")
				.and()
				.withUser("admin").password("{noop}password").roles("ADMIN")
		         .and()
				.withUser("super_admin").password("{noop}password").roles("SUPER_ADMIN")
				.and()
				.withUser("none").password("{noop}password").roles("NONE");

	}


	@Autowired
	AuthenticationSuccessHandler successHandler;

//	The WebSecurityConfig class is annotated with @EnableWebSecurity to enable
//	Spring Security’s web security support and provide the Spring MVC integration.
//	It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods
//	to set some specifics of the web security configuration.

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**",
						"/",  "./css/","/register").permitAll()
//				.antMatchers("/organizational_officer").hasAnyRole("USER")
				.antMatchers("/admin").hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
//				.antMatchers("/list_room","/ListMeetings", "/list_organization",
//						"/add_meeting" ,"/add_role", "/listRoles"
//						).hasAnyRole("USER", "ADMIN")
//
//				.antMatchers("/users","/admin",
//						"/organizational_officer",
//						"/delete_user/{id}", "/save_user",
//						"/boardrooms").hasAnyRole("ADMIN")

				.antMatchers("/list_room","/ListMeetings","/list_organization",
						"/ListUsers","/admin",
						"/organizational_officer",
						"/delete_user/{id}", "/save_user","/users", "/add_meeting", "/add_role",
						 "/listRoles", "/profile1","/edit_profile","/list_contacts").authenticated()

			.anyRequest().permitAll()
			.and()
				.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login").permitAll()
				.usernameParameter("txtUsername")
				.passwordParameter("txtPassword")
//			.formLogin().loginPage("/login").permitAll()
////				.formLogin().loginPage("/login")
//				.usernameParameter("email")
//				.passwordParameter("password")
//				.defaultSuccessUrl("/organizational_officer")
				.successHandler(successHandler)

				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
//				.and()
//				.exceptionHandling().accessDeniedPage("/admin")
//		;
	}
}

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/user").hasAnyRole("USER")
//				.antMatchers("/admin").hasAnyRole("ADMIN")
//				.and().formLogin().loginPage("/login")
//				.successHandler(successHandler)
//				.permitAll()
//				.and().logout();
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/css/**", "/js/**", "/images/**",
//						"/", "/register", "./css/").permitAll()
//				.antMatchers("/users", "User/list_users",
//						"/organizational_officer", "/edit_user/{id}",
//						"/delete_user/{id}", "/save_user", "/organizations",
//						"/boardrooms", "/employees").authenticated()
//				.anyRequest().permitAll()
//				.and()
//				.formLogin()
//				.usernameParameter("email")
//				.defaultSuccessUrl("/organizational_officer")
//				.permitAll()
//				.and()
//				.logout().logoutSuccessUrl("/").permitAll();
//	}
