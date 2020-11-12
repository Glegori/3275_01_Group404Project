package com.csis3275.Group404Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * Checks that the username entered matches the password from the database. Depending on the role and whether the password is successful,
 * it will show either an error page or the homepage.
 *
 */

@EnableWebSecurity
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {


    @Qualifier("loginUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;


    /**
     * Authorizes username and password inputted.
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    /**
     * Redirects the page based on the authorization.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/error").permitAll()
                .antMatchers("/").permitAll()
                .and().formLogin().defaultSuccessUrl("/homePage");

    }
    /**
     * Password encoder method.
     * @return No encoder.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){

        return NoOpPasswordEncoder.getInstance();

    }

}
