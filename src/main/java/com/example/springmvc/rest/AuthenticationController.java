package com.example.springmvc.rest;


import com.example.springmvc.DTO_Class.AuthenticationRequest;
import com.example.springmvc.DTO_Class.AuthenticationResponse;
import com.example.springmvc.dao.AccountRespository;
import com.example.springmvc.entity.Login.Account;
import com.example.springmvc.jwt.JwtUtil;
import com.example.springmvc.service.class_service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JwtUtil jwtUtil;

    private final AccountServiceImpl accountService;

    @Autowired
    public AuthenticationController(JwtUtil jwtUtil, AccountServiceImpl accountService) {
        this.jwtUtil = jwtUtil;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        System.out.println("Starting authentication...");

        // Authenticate user
        Account account = authenticate(request.getUsername(), request.getPassword());
        if (account == null) {
            System.out.println("Account not found or invalid credentials");
            throw new RuntimeException("Invalid credential account");
        }

        // Extract role from account
        String roleName = account.getRole().getRole_name();
        if (roleName == null || roleName.isEmpty()) {
            System.out.println("Role not found or invalid role name");
            throw new RuntimeException("Invalid credential rolename");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(account.getUsername(), roleName);

        System.out.println("Token generated successfully");

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    public Account authenticate(String username, String password) {
        try {
            System.out.println("Attempting to authenticate user: " + username);
            Account account = accountService.getAccountByUserName(username);

            if (account == null) {
                throw new RuntimeException("Invalid username"); // Tài khoản không tồn tại
            }

            System.out.println("Account found. Checking password...");

            // Sử dụng BCrypt để so sánh mật khẩu
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(password, account.getPassword())) {
                throw new RuntimeException("Invalid password"); // Mật khẩu không đúng
            }

            return account; // Tài khoản hợp lệ

        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            throw new RuntimeException("Invalid credential", e);
        }
    }



}