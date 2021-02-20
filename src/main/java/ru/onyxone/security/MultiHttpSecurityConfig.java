package ru.onyxone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public MultiHttpSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()

                    //.antMatcher("/rest/users/**")
                    .antMatcher("/rest/**")
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/rest/users","/rest/users/").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET, "/rest/users/**").hasAuthority("USER")
                    .antMatchers(HttpMethod.POST, "/rest/users").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/rest/users/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/rest/users/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();

        }
    }

    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        private final SuccessUserHandler successUserHandler;

        @Autowired
        public FormLoginWebSecurityConfigurerAdapter(SuccessUserHandler successUserHandler) {
            this.successUserHandler = successUserHandler;
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .formLogin()
                    .successHandler(successUserHandler)
                    .permitAll();

            http.logout()
                    .permitAll()
                    .logoutSuccessUrl("/login");

            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/login", "/registration").permitAll()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/user/**").hasAuthority("USER")
                    .antMatchers(HttpMethod.GET, "/rest/users","/rest/users/").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET, "/rest/users/**").hasAuthority("USER")
                    .antMatchers(HttpMethod.POST, "/rest/users").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/rest/users/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/rest/users/**").hasAuthority("ADMIN");
        }
    }
}
