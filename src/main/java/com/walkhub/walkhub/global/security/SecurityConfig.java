package com.walkhub.walkhub.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkhub.walkhub.global.error.CustomAuthenticationEntryPoint;
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
                .antMatchers(HttpMethod.PATCH, "/users").authenticated()
                .antMatchers(HttpMethod.POST, "/users/classes").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/users/classes").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/users/accounts/{phone-number}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/health").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/goal").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/schools").authenticated()

                //levels
                .antMatchers(HttpMethod.GET, "/levels/lists").authenticated()
                .antMatchers(HttpMethod.PATCH, "/levels/{level-id}").authenticated()

                // badges
                .antMatchers(HttpMethod.GET, "/badges/{user-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/badges").authenticated()
                .antMatchers(HttpMethod.PUT, "/badges/{badge-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/badges/new").authenticated()

                // exercises
                .antMatchers(HttpMethod.POST, "/exercises").authenticated()
                .antMatchers(HttpMethod.PATCH, "/exercises/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/exercises").authenticated()
                .antMatchers(HttpMethod.POST, "/exercises/locations/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/analysis").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/lists").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/{exercise-id}").hasAnyAuthority("TEACHER", "ROOT")

                // notices
                .antMatchers(HttpMethod.GET, "/notices/list").authenticated()
                .antMatchers(HttpMethod.POST, "/notices").hasAnyAuthority("ROOT", "SU")
                .antMatchers(HttpMethod.DELETE, "/notices/{notice-id}").hasAnyAuthority("ROOT", "SU")

                // notifications
                .antMatchers(HttpMethod.GET, "/notifications").authenticated()
                .antMatchers(HttpMethod.PATCH, "/notifications/{notification-id}").authenticated()

                // ranks
                .antMatchers(HttpMethod.GET, "/ranks/schools").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/schools/search").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/{school-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/my-school").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/search").authenticated()

                // challenges
                .antMatchers(HttpMethod.POST, "/challenges").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.PATCH, "/challenges/{challenge-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.DELETE, "/challenges/{challenge-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}/progress").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.POST, "/challenges/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}/participants/students").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}/participants/teachers").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/challenges/list").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/list/closed").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/participated").authenticated()

                // images
                .antMatchers(HttpMethod.POST, "/images").permitAll()

                // schools
                .antMatchers(HttpMethod.PATCH, "/schools/logos").hasAuthority("ROOT")
                .antMatchers(HttpMethod.GET, "/schools/search").permitAll()

                // teachers
                .antMatchers(HttpMethod.POST, "/teachers/verification-codes").hasAuthority("ROOT")
                .antMatchers(HttpMethod.PATCH, "/teachers/verification-codes").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/teachers/users/search").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.GET, "/teachers/users").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.POST, "/teachers/classes").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.GET, "/teachers/classes/lists").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.DELETE, "/teachers/classes/{section-id}").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.GET, "/teachers/classes").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET, "/teachers/users/{user-id}").hasAnyAuthority("TEACHER")
                .antMatchers(HttpMethod.GET, "/teachers/users").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.PATCH, "/teachers/schools").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET, "/teachers/students/verification-codes").hasAnyAuthority("TEACHER")
                .antMatchers(HttpMethod.PATCH, "/teachers/classes/verification-codes").hasAuthority("TEACHER")

                // su
                .antMatchers(HttpMethod.GET, "/su").hasAuthority("SU")
                .antMatchers(HttpMethod.POST, "/su/accounts/{school-id}").hasAuthority("SU")

                //excel
                .antMatchers(HttpMethod.GET, "/excel").hasAnyAuthority("TEACHER", "ROOT")

                // socket.io
                .antMatchers(HttpMethod.GET, "/socket.io").authenticated()

                .anyRequest().denyAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
    }

}

