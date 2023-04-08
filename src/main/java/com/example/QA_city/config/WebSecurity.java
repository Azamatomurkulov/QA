package com.example.QA_city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user")
                .password(getPasswordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(getPasswordEncoder().encode("admin"))
                .roles("ADMIN");
    }
    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/street/**","/shop/**","/city/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/street/**","/shop/**","/city/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/street/**","/shop/**","/city/**").hasRole("ADMIN")

                .and().httpBasic();
    }
}
