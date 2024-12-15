package com.example.springmvc.security;

import com.example.springmvc.jwt.JwtAuthenticationEntryPoint;
import com.example.springmvc.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Cấu hình bảo mật cho HTTP
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeRequests(authz -> authz
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/forgot-password").permitAll()
                        .requestMatchers("/api/auth/reset-password").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/notifications/**").hasAnyAuthority("ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/mealPlans/approved").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER","ROLE_CLIENT")
                        .requestMatchers("/mealPlans/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/programs/approved").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER","ROLE_CLIENT")
                        .requestMatchers("/programs/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/exercise/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/food/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/clientstracking/getClientsTrackingByToken").hasAnyAuthority("ROLE_CLIENT")
                        .requestMatchers("/clientstracking/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .requestMatchers("/client/getClientInforByToken").hasAnyAuthority("ROLE_CLIENT")
                        .requestMatchers("/client/**").hasAnyAuthority("ROLE_PERSONAL_TRAINER","ROLE_FITNESS_MANAGER","ROLE_OWNER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint) // Xử lý lỗi xác thực
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Tắt session
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Thêm JWT filter

        return http.build(); // Xây dựng SecurityFilterChain
    }

    // Cấu hình CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Cho phép domain FE
        configuration.addAllowedMethod("*"); // Cho phép tất cả các HTTP method
        configuration.addAllowedHeader("*"); // Cho phép tất cả các header
        configuration.setAllowCredentials(true); // Cho phép gửi cookie hoặc thông tin xác thực
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("Content-Type");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả các endpoint
        return source;
    }

}

