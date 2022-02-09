package com.walkhub.walkhub.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // users
                .antMatchers(HttpMethod.POST, "/users/verification-codes").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/token").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/token").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/password").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{user-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/users/{user-id}/badges").authenticated()
                .antMatchers(HttpMethod.PUT, "/users/badges/{badge-id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/users/accounts/{phone-number}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/healths").authenticated()
                .antMatchers(HttpMethod.POST, "/users/classes/{agency-code}/{grade}/{class}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/school").authenticated()

                // exercises
                .antMatchers(HttpMethod.POST, "/exercises").authenticated()
                .antMatchers(HttpMethod.PATCH, "/exercises/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/exercises").authenticated()
                .antMatchers(HttpMethod.POST, "/exercises/locations/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/analysis").authenticated()

                // notices
                .antMatchers(HttpMethod.GET, "/notices/list").authenticated()
                .antMatchers(HttpMethod.POST, "/notices").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.DELETE, "/notices/{notice-id}").hasAnyAuthority("TCHR", "ROOT")

                // notifications
                .antMatchers(HttpMethod.GET, "/notifications").authenticated()
                .antMatchers(HttpMethod.PATCH, "/notifications/{notification-id}").authenticated()

                // ranks
                .antMatchers(HttpMethod.GET, "/ranks/schools").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/schools/search").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/{agency-code}").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/my-school").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/search").authenticated()

                // challenges
                .antMatchers(HttpMethod.POST, "/challenges").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.PATCH, "/challenges/{challenge-id}").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.DELETE, "/challenges/{challenge-id}").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.GET, "/challenges/list").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.POST, "/challenges/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}/participants").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/participated").authenticated()

                // images
                .antMatchers(HttpMethod.POST, "/images").permitAll()

                // schools
                .antMatchers(HttpMethod.PATCH, "/schools/logos/{agency-code}").hasAuthority("ROOT")
                .antMatchers(HttpMethod.GET, "/schools/search").authenticated()

                // teachers
                .antMatchers(HttpMethod.POST, "/teachers/verification-codes").hasAuthority("ROOT")
                .antMatchers(HttpMethod.POST, "/teachers/classes").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.DELETE, "/teachers/classes/{agency-code}/{grade}/{class}").hasAnyAuthority("TCHR", "ROOT")
                .antMatchers(HttpMethod.PATCH,  "/teachers/classes/verification-codes").hasAuthority("TCHR")

                // socket.io
                .antMatchers(HttpMethod.GET, "/socket.io").permitAll()
                .anyRequest().denyAll()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
    }

}

