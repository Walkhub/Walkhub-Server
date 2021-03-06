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
                .antMatchers(HttpMethod.HEAD, "/users/classes").authenticated()
                .antMatchers(HttpMethod.HEAD, "/users/account-id").permitAll()
                .antMatchers(HttpMethod.HEAD, "/users/verification-codes").permitAll()
                .antMatchers(HttpMethod.GET, "/users/auth/info").authenticated()
                .antMatchers(HttpMethod.GET, "/users/health").authenticated()
                .antMatchers(HttpMethod.GET, "/users/info").authenticated()
                .antMatchers(HttpMethod.GET, "/users/goal").authenticated()
                .antMatchers(HttpMethod.GET, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/users/accounts/{phone-number}").authenticated()
                .antMatchers(HttpMethod.GET, "/users/{user-id}").authenticated()
                .antMatchers(HttpMethod.POST, "/users/token").permitAll()
                .antMatchers(HttpMethod.POST, "/users/verification-codes").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/classes").authenticated()
                .antMatchers(HttpMethod.POST, "/users/verification-password").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/{user-id}/independence").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.PATCH, "/users/goal").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/token").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/health").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/password").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/classes").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/logout").authenticated()

                //levels
                .antMatchers(HttpMethod.GET, "/levels/lists").authenticated()
                .antMatchers(HttpMethod.PATCH, "/levels/{level-id}").authenticated()

                // exercises
                .antMatchers(HttpMethod.GET, "/exercises/lists").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/history/{user-id}").hasAnyAuthority("TEACHER", "ROOT")
                .antMatchers(HttpMethod.GET, "/exercises/analysis").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/exercises/users/lists").permitAll()
                .antMatchers(HttpMethod.POST, "/exercises").authenticated()
                .antMatchers(HttpMethod.POST, "/exercises/locations/{exercise-id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/exercises").authenticated()
                .antMatchers(HttpMethod.PATCH, "/exercises/{exercise-id}").authenticated()

                // notices
                .antMatchers(HttpMethod.GET, "/notices/list").authenticated()
                .antMatchers(HttpMethod.POST, "/notices").hasAnyAuthority("ROOT", "SU")
                .antMatchers(HttpMethod.PATCH, "/notices/{notice-id}").hasAnyAuthority("ROOT", "SU")
                .antMatchers(HttpMethod.DELETE, "/notices/{notice-id}").hasAnyAuthority("ROOT", "SU")

                // notifications
                .antMatchers(HttpMethod.GET, "/notifications").authenticated()
                .antMatchers(HttpMethod.PATCH, "/notifications/{notification-id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/notifications/on").authenticated()
                .antMatchers(HttpMethod.PATCH, "/notifications").authenticated()
                .antMatchers(HttpMethod.GET, "/notifications/status").authenticated()

                // ranks
                .antMatchers(HttpMethod.GET, "/ranks/schools").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/schools/search").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/{school-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/my-school").authenticated()
                .antMatchers(HttpMethod.GET, "/ranks/users/search/{school-id}").authenticated()

                // challenges
                .antMatchers(HttpMethod.GET, "/challenges/app/list").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/app/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/challenges/web/lists").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/challenges/web/{challenge-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/challenges/{challenge-id}/progress").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/challenges/participated").authenticated()
                .antMatchers(HttpMethod.POST, "/challenges").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.POST, "/challenges/{challenge-id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/challenges/{challenge-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.DELETE, "/challenges/{challenge-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")

                // badges
                .antMatchers(HttpMethod.GET, "/badges/{user-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/badges/new").authenticated()
                .antMatchers(HttpMethod.GET, "/badges").authenticated()
                .antMatchers(HttpMethod.PUT, "/badges/{badge-id}").authenticated()

                // images
                .antMatchers(HttpMethod.POST, "/images").permitAll()

                // schools
                .antMatchers(HttpMethod.GET, "/schools/details/{school-id}").authenticated()
                .antMatchers(HttpMethod.GET, "/schools/search").permitAll()
                .antMatchers(HttpMethod.PATCH, "/schools/logos").hasAuthority("ROOT")

                // teachers
                .antMatchers(HttpMethod.GET, "/teachers/classes/{section-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/teachers/lists").hasAuthority("ROOT")
                .antMatchers(HttpMethod.GET, "/teachers/users/{user-id}").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/teachers/users/search").hasAnyAuthority("TEACHER", "ROOT", "SU")
                .antMatchers(HttpMethod.GET, "/teachers/my-class").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.GET, "/teachers/classes/lists").hasAnyAuthority("ROOT", "SU")
                .antMatchers(HttpMethod.POST, "/teachers/verification-codes").hasAuthority("ROOT")
                .antMatchers(HttpMethod.POST, "/teachers/classes").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.PATCH, "/teachers/verification-codes").hasAuthority("USER")
                .antMatchers(HttpMethod.PATCH, "/teachers/schools").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.DELETE, "/teachers/classes/{section-id}").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.DELETE, "/teachers/{teacher-id}").hasAuthority("ROOT")

                // su
                .antMatchers(HttpMethod.POST, "/su/accounts/{school-id}").hasAuthority("SU")
                .antMatchers(HttpMethod.PATCH, "/su/accounts/{school-id}").hasAuthority("SU")

                //excel
                .antMatchers(HttpMethod.GET, "/excel/users").hasAnyAuthority("TEACHER", "ROOT")

                // socket.io
                .antMatchers(HttpMethod.GET, "/socket.io").authenticated()

                .anyRequest().denyAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
    }

}

