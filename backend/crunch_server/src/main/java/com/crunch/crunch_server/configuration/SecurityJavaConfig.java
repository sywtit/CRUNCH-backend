package com.crunch.crunch_server.configuration;

import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityJavaConfig extends WebSecurityConfigurerAdapter
{
    @Value("{jwt.secret}")
    private String secret;

    private final String[] PERMITTED_URL = {
        "","/","/api/user/account/signup", "/api/user/account/auth",
        "/project/search/**","/user","/user/**","/api/**","/login"
    };

    private final String[] UNPERMITTED_URL = { 
        "/users/**", "/project/work/**", "/notice/**"
    };

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/myinfo").hasRole("MEMBER")
                .antMatchers("/project/work").hasRole("MEMBER")
                .antMatchers("/**").permitAll();
                // .and()
                // // login configuration
                // .formLogin()
                //     .loginPage("/user/login")
                //     .failureUrl("/user/login?error=1")
                //     .defaultSuccessUrl("/", false)
                // .and()
                // // log out configuration
                // .logout()
                //     .logoutUrl("/logout")
                //     .logoutSuccessUrl("/")
                //     .invalidateHttpSession(true)
                //     .deleteCookies("JSESSIONID")
                //     .clearAuthentication(true)
                //     .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/", "/js/**", "/css/**", "/images/**");

    }
   
    @Bean
    public JwtUtil jwtUtil()
    {
        return new JwtUtil(secret);
    }
}