package antifraud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static antifraud.dto.UserRoles.*;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults()) // enable default basic authentication
                .csrf(CsrfConfigurer::disable) // allows for post requests via Postman
                .headers(headers -> headers.frameOptions().disable())
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(new RestAuthenticationEntryPoint());
                    handling.accessDeniedHandler(new RestAccessDeniedHandler());
                })
                .authorizeHttpRequests(auth -> { // define authorization controls
                    auth
                            // authorization required
                            .requestMatchers(HttpMethod.GET, "/api/auth/list/**").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/auth/user/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/antifraud/transaction/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/auth/user/**").permitAll()
                            .requestMatchers("/error/**").permitAll()
                            .requestMatchers("/actuator/**").permitAll() // enable for testing purposes
                            .requestMatchers("/h2-console/**").permitAll() // enable h2 console to inspect db
                            .anyRequest().permitAll();
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no session
                .build();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
