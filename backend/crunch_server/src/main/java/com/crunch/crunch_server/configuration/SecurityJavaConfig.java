package com.crunch.crunch_server.configuration;

import javax.servlet.http.HttpServletResponse;

import com.crunch.crunch_server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter
{
    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/myinfo").hasRole("MEMBER")
                .antMatchers("/api/project/work").hasRole("MEMBER")
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
                .anyRequest().permitAll();
        http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable()
        .and()
            .exceptionHandling()
                .authenticationEntryPoint((req,rsp,e)-> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/user/acccount/auth")
                .failureUrl("/user/login?result=fail")
                .defaultSuccessUrl("/",true)
                .usernameParameter("identity")
                .passwordParameter("password");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true);
       
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

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     super.configure(http); //
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();

    //     configuration.addAllowedOrigin("http://localhost:3000");
    //     configuration.addAllowedHeader("*");
    //     configuration.addAllowedMethod("*");
    //     configuration.setAllowCredentials(true);

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }

}