
package com.egg.library.config;

import com.egg.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(encoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                // TODO FIX BUG - After first login, it redirects to "http://localhost:8080/style.css"

                .and()

                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .deleteCookies("JSESSIONID")

                .and()
                .csrf().disable();

        /*
                http
                .authorizeRequests()
                    .antMatchers("/css/*", "/", "/registro", "/registrar").permitAll()
                    .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                .and()
                    .csrf().disable();

            */

    }
}
