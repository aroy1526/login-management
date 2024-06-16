package com.loginManagement.lms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class BasicAuthenticationConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    // Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.
                frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests((authorize)->{
            //Role based Security Config Start
            /*authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");


            //hashAnyRole - use for giving access to multiple ROLES
            authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("USER","ADMIN","MANAGER");
            //hashRole - user for giving access to single ROle
            authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasRole("MANAGER");

            authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole("ADMIN","MANAGER");*/
                    //Role based Security Config end
            authorize.requestMatchers("/h2-console/**").permitAll();
            authorize.requestMatchers("/api/auth/**").permitAll();
            authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                    authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    //Spring Security in memory User
    /*@Bean
    public UserDetailsService userDetails() {
        UserDetails adminUserDetails = User.builder().username("Admin")
                .password(passwordEncoder().encode("password")).
                roles("ADMIN")
                .build();
        UserDetails userDetails = User.builder().
                username("user").
                password(passwordEncoder().encode("user")).
                roles("USER").
                build();
        UserDetails managerDetails = User.builder()
                .roles("MANAGER").password(passwordEncoder().encode("manager")).username("manager")
                .build();
        return new InMemoryUserDetailsManager(adminUserDetails,userDetails,managerDetails);
    }
*/
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
    }
}
