package com.example.springmvc.service.class_service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private boolean startTlsEnable;

    public void sendResetPasswordEmail(String toEmail, String token) {
        String subject = "Khôi phục mật khẩu";
        String resetUrl = "http://localhost:3000/reset-password?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
        String body = "Nhấn vào liên kết sau để đặt lại mật khẩu: " + resetUrl;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            // Thiết lập thông tin email
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            // Gửi email
            mailSender.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra khi gửi email: " + e.getMessage());
        }
    }

    @Autowired
    public void configureMailSender() {
        JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) mailSender;

        // Cấu hình các thuộc tính cho JavaMailSenderImpl
        mailSenderImpl.setHost(host);
        mailSenderImpl.setPort(port);
        mailSenderImpl.setUsername(username);
        mailSenderImpl.setPassword(password);

        // Thiết lập các thuộc tính bảo mật cho kết nối SMTP
        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", startTlsEnable);
        properties.put("mail.smtp.ssl.trust", host); // Nếu sử dụng SSL (nếu có)
    }
}
