package com.AnnualLeaveApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //configure the paths that will be accessed by user and by admin
    //in case of accessing the wrong path it will throw a 403 forbidden error
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('MANAGER')")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails theUser = User.withUsername("sonja")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("sonja123").roles("USER").build();

        //Manager Role
        UserDetails theManager = User.withUsername("klaus")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("klaus123").roles("MANAGER").build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);
        userDetailsManager.createUser(theManager);

        return userDetailsManager;
    }
}