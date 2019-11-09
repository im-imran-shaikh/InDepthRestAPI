package com.imran.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigureAppSecurity extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;

	public AuthenticationProvider getAuthenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("ADMIN");
//
//    }
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService()
//	{
//		List<UserDetails> userDetails = new ArrayList<>();
//		userDetails.add(User.withDefaultPasswordEncoder().username("imran").password("123").roles("admin").build());
//		userDetails.add(User.withDefaultPasswordEncoder().username("iqbal").password("123").roles("user").build());
//		userDetails.add(User.withDefaultPasswordEncoder().username("suraj").password("123").roles("user").build());
//		
//		return new InMemoryUserDetailsManager(userDetails);
//	}
}
