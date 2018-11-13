package com.wellness.sseproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;
    @Autowired
    JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // Un-secure H2 Database
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/courses/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/orders/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/lectures/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/files/**").hasAnyRole("ADMIN")
                .antMatchers("/boards/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/feeds/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/users").permitAll() // allow Register User
                .anyRequest().permitAll();
                //.anyRequest().hasAnyRole("ADMIN","USER").and();


        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl();
    }

}