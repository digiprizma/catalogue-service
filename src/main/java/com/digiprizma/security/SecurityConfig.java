package com.digiprizma.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = getBCPE();
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "USER");
//		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("1234")).roles("ADMIN", "USER");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// make stateless authetntif
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests().antMatchers("/users/**","/logins/**");
		http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilterBefore(new JWTAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
		// desactivate security usage
		// super.configure(http);
	}

//	@Bean
//	BCryptPasswordEncoder getBCPE() {
//		return new BCryptPasswordEncoder();
//	}

}
