package com.org.girapets.girapets;

import com.org.girapets.girapets.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class Configurations {

    @Autowired
    private FilterToken filter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic().and().cors().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()

                //Requisições livres para todos
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/home").permitAll()
                .antMatchers(HttpMethod.GET, "/api/animais").permitAll()
                .antMatchers(HttpMethod.GET, "/api/imagens/**").permitAll()

                //Requisições que necessitam de autenticação
                .antMatchers(HttpMethod.POST, "/api/imagens").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/imagens/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/imagens").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/imagens/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/animais").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/animais/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/animais").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/animais/**").authenticated()
                .anyRequest().authenticated().and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic().and().cors().disable()
                .httpBasic().and().csrf().disable().build();
    }

 /*
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Henrique")
                .password((passwordEncoder().encode("123")))
                .roles("ROLE-USER");
    }

  */
    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }


    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();

    }


}
