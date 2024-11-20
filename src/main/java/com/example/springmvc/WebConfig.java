//package com.example.springmvc;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Cho phép tất cả các request từ localhost:3000
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Các phương thức HTTP bạn muốn cho phép
//                .allowedHeaders("*")  // Cho phép tất cả các headers
//                .allowCredentials(true);  // Cho phép gửi cookie
//    }
//}
//
