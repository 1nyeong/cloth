package com.study.clothclone.config;

import com.study.clothclone.security.AuthFailureHandler;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeHttpRequests()
                .antMatchers("/account/mypage","/index")
                .authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
//                .antMatchers("/admin/**","/api/admin/**")
//                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/account/login")                 //login page 요청
                .loginProcessingUrl("/account/login")        //login service post 요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/index");
    }
}
