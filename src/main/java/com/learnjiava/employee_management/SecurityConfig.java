package com.learnjiava.employee_management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learnjiava.employee_management.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			return http
				.csrf(csrf -> csrf.disable())
					.sessionManagement(sess -> sess
									.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authorizeHttpRequests(auth -> auth
						.requestMatchers(
							"/",
							"/index.html",
							"/employee/**",
							"/login",
							"/register",
							"/employees/statistic",
							"/auth/**",
							"/swagger-ui/**"
            ).permitAll()

						.anyRequest().authenticated()
					)
					.addFilterBefore(jwtAuthFilter,
									UsernamePasswordAuthenticationFilter.class)
					.build();
    }
}
