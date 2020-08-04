package com.imran.security.spring_security;

import com.imran.security.jwt.JwtTokenVerification;
import com.imran.security.jwt.JwtUsernamePasswordAutheticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguretion extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // jwt is stateless
                .and()
                .addFilter(new JwtUsernamePasswordAutheticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerification(), JwtUsernamePasswordAutheticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated();



//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/successfull",true)
//                .and()

                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // if you want ot accesses CSRF the create a token and pass it in to header
    }


    protected AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("imran")
//                .password(passwordEncoder.encode("shaikh"))
//                .roles("ADMIN")
//                .and()
//                .withUser("iqbal")
//                .password(passwordEncoder.encode("shaikh"))
//                .roles("USER");
//    }


}