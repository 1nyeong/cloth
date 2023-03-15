package com.study.clothclone.config;

import com.study.clothclone.security.AuthFailureHandler;
import com.study.clothclone.service.PrincipalOauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2Service principalOauth2Service;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/checkout",  "/board", "/boarddelete", "/boardmodify/{id}")
                .authenticated()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
                .antMatchers("/admin/**", "/api/admin/**")
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/login")            // login page Get요청
                .loginProcessingUrl("/login")   // login service Post요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/all")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(principalOauth2Service)
                .and()
                .defaultSuccessUrl("/all");
    }
}
