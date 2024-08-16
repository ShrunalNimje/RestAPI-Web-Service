package my.mood.restAPI.RestAPI.Web.Service.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// SecurityFilterChain
		// 1) All requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		
		// 2) If request is not authenticated, a pop up is shown
		http.httpBasic(withDefaults());
		
		// 3) CSRF -> post, put
		http.csrf().disable();
		
		return http.build();
	}
	
}
