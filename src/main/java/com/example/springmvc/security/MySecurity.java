//package com.example.springmvc.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MySecurity {
//    @Bean
//    @Autowired
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT a.username, r.rolename FROM account a JOIN role r ON a.role_id = r.role_id WHERE a.username=?");
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests(configurer -> configurer
////                        .anyRequest().authenticated() // hoặc .permitAll() tùy nhu cầu của bạn
////                )
////                .formLogin(form -> form.loginPage("/showLoginPage")
////                        .loginProcessingUrl("/authenticateTheUser")
////                        .permitAll());
////
////        return http.build();
////    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                configurer -> configurer
//                        .requestMatchers("/index").permitAll()
//                        .requestMatchers("/mealPlans/createMealPlan").hasAuthority("Personal Trainer")
//                        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
//                        .requestMatchers("/teacher/**").hasAnyRole("ADMIN", "MANAGER", "TEACHER")
//                        .anyRequest().permitAll()
//        ).formLogin(
//                form -> form.loginPage("/showLoginPage")
//                        .loginProcessingUrl("/authenticateTheUser")
//                        .defaultSuccessUrl("/mealPlans/createMealPlan", true)
//                        .permitAll()
//        ).logout(
//                logout -> logout.permitAll()
//        ).exceptionHandling(
//                configurer -> configurer.accessDeniedPage("/error403")
//        );
//
//        return http.build();
//    }
//}
