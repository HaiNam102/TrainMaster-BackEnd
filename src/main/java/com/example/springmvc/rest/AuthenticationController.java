package com.example.springmvc.rest;


import com.example.springmvc.DTO_Class.AccountDTO;
import com.example.springmvc.DTO_Class.AuthenticationRequest;
import com.example.springmvc.DTO_Class.AuthenticationResponse;
import com.example.springmvc.dao.RoleRepository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.FitnessManager;
import com.example.springmvc.entity.Login.Account;
import com.example.springmvc.entity.Login.Role;
import com.example.springmvc.entity.Owner;
import com.example.springmvc.entity.PersonalTrainer;
import com.example.springmvc.jwt.JwtUtil;
import com.example.springmvc.service.class_service.*;
import com.example.springmvc.service.interface_service.ClientService;
import com.example.springmvc.service.interface_service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final PersonalTrainerServiceImpl personalTrainerService;

    private final FitnessManagerServiceImpl fitnessManagerService;

    private final OwnerServiceImpl ownerService;

    private final ClientServiceImpl clientService;

    private final JwtUtil jwtUtil;

    private final AccountServiceImpl accountService;

    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationController(PersonalTrainerServiceImpl personalTrainerService, FitnessManagerServiceImpl fitnessManagerService, OwnerServiceImpl ownerService, ClientServiceImpl clientService, JwtUtil jwtUtil, AccountServiceImpl accountService, RoleRepository roleRepository ) {
        this.personalTrainerService = personalTrainerService;
        this.fitnessManagerService = fitnessManagerService;
        this.ownerService = ownerService;
        this.clientService = clientService;
        this.jwtUtil = jwtUtil;
        this.accountService = accountService;
        this.roleRepository =roleRepository;
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
        String roleName = account.getRole().getRoleName();
        if (roleName == null || roleName.isEmpty()) {
            System.out.println("Role not found or invalid role name");
            throw new RuntimeException("Invalid credential rolename");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(account.getUsername(), roleName);

        System.out.println("Token generated successfully");

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        Role role = roleRepository.findByRoleName(accountDTO.getRoleName());
        if (role == null) {
            return ResponseEntity.badRequest().body("Invalid role name");
        }

        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
        account.setEnabled(true);
        account.setRole(role);
        Account Updateaccount = accountService.register(account);

        switch (accountDTO.getRoleName()) {
            case "Owner":
                Owner owner = new Owner();
                owner.setAccount(Updateaccount);
                owner.setFirstName(accountDTO.getFirstName());
                owner.setLastName(accountDTO.getLastName());
                owner.setGender(accountDTO.getGender());
                owner.setBirthDate(accountDTO.getBirthDate());
                owner.setPhone(accountDTO.getPhone());
                owner.setAddress(accountDTO.getAddress());
                owner.setEmail(accountDTO.getEmail());
                ownerService.updateOwner(owner);
                break;

            case "Personal_Trainer":
                PersonalTrainer pt = new PersonalTrainer();
                pt.setAccount(Updateaccount);
                pt.setFirstName(accountDTO.getFirstName());
                pt.setLastName(accountDTO.getLastName());
                pt.setGender(accountDTO.getGender());
                pt.setBirthDate(accountDTO.getBirthDate());
                pt.setPhone(accountDTO.getPhone());
                pt.setAddress(accountDTO.getAddress());
                pt.setEmail(accountDTO.getEmail());
                personalTrainerService.updatePersonalTrainer(pt);
                break;

            case "Client":
                Client client = new Client();
                client.setAccount(Updateaccount);
                client.setFirstName(accountDTO.getFirstName());
                client.setLastName(accountDTO.getLastName());
                client.setGender(accountDTO.getGender());
                client.setBirthDate(accountDTO.getBirthDate());
                client.setPhone(accountDTO.getPhone());
                client.setAddress(accountDTO.getAddress());
                client.setEmail(accountDTO.getEmail());
                clientService.updateClient(client);
                break;

            case "Fitness_Manager":
                FitnessManager fm = new FitnessManager();
                fm.setAccount(Updateaccount);
                fm.setFirstName(accountDTO.getFirstName());
                fm.setLastName(accountDTO.getLastName());
                fm.setGender(accountDTO.getGender());
                fm.setBirthDate(accountDTO.getBirthDate());
                fm.setPhone(accountDTO.getPhone());
                fm.setAddress(accountDTO.getAddress());
                fm.setEmail(accountDTO.getEmail());
                fitnessManagerService.updateFitnessManager(fm);
                break;

            default:
                return ResponseEntity.badRequest().body("Invalid role specified!");
        }
        return ResponseEntity.ok("Registration successful!");
    }
    @GetMapping("/PersonalTrainer/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        // Loại bỏ tiền tố "Bearer "
        token = token.replace("Bearer ", "");

        String username = jwtUtil.extractUsername(token);

        // Tìm account từ username
        Account account = accountService.getAccountByUserName(username);
        if (account == null) {
            return ResponseEntity.status(404).body("Không tìm thấy account với username: " + username);
        }

        // Tìm PersonalTrainer từ account_id
        PersonalTrainer personalTrainer = personalTrainerService.findByAccount(account);
        if (personalTrainer == null) {
            return ResponseEntity.status(404).body("Không tìm thấy PersonalTrainer cho account_id: " + account.getAccountId());
        }
        return ResponseEntity.ok(personalTrainer);
    }

    @GetMapping("/FitnessManager/info")
    public ResponseEntity<?> getFitnessInfo(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer" , "");
        String username = jwtUtil.extractUsername(token);

        Account account =accountService.getAccountByUserName(username);
        if(account == null){
            return ResponseEntity.status(404).body("Not found" + username);
        }
        FitnessManager fitnessManager = fitnessManagerService.findFitnessManagerByAccount(account);
        if(fitnessManager == null){
            return ResponseEntity.status(404).body("Not found" +account.getAccountId());
        }

        return ResponseEntity.ok(fitnessManager);
    }

    @PutMapping("/FitnessManager/update")
    public ResponseEntity<?> updateFitnessManagerInfo(@RequestHeader("Authorization") String token,
                                            @RequestBody FitnessManager updatedTrainer) {
        try {
            // Loại bỏ tiền tố "Bearer " từ token
            token = token.replace("Bearer ", "");

            // Lấy username từ JWT
            String username = jwtUtil.extractUsername(token);

            // Tìm account từ username
            Account account = accountService.getAccountByUserName(username);
            if (account == null) {
                return ResponseEntity.status(404).body("Không tìm thấy account với username: " + username);
            }

            // Tìm PersonalTrainer từ account_id
            FitnessManager fitnessManager = fitnessManagerService.findFitnessManagerByAccount(account);
            if (fitnessManager == null) {
                return ResponseEntity.status(404).body("Not found " + account.getAccountId());
            }

            // Cập nhật thông tin Personal Trainer
            fitnessManager.setFirstName(updatedTrainer.getFirstName());
            fitnessManager.setLastName(updatedTrainer.getLastName());
            fitnessManager.setGender(updatedTrainer.getGender());
            fitnessManager.setBirthDate(updatedTrainer.getBirthDate());
            fitnessManager.setPhone(updatedTrainer.getPhone());
            fitnessManager.setAddress(updatedTrainer.getAddress());
            fitnessManager.setEmail(updatedTrainer.getEmail());

            // Lưu thay đổi
            fitnessManagerService.updateFitnessManager(fitnessManager);

            return ResponseEntity.ok("Cập nhật thông tin Fitness Manager thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @PutMapping("/PersonalTrainer/update")
    public ResponseEntity<?> updateUserInfo(@RequestHeader("Authorization") String token,
                                            @RequestBody PersonalTrainer updatedTrainer) {
        try {
            // Loại bỏ tiền tố "Bearer " từ token
            token = token.replace("Bearer ", "");

            // Lấy username từ JWT
            String username = jwtUtil.extractUsername(token);

            // Tìm account từ username
            Account account = accountService.getAccountByUserName(username);
            if (account == null) {
                return ResponseEntity.status(404).body("Không tìm thấy account với username: " + username);
            }

            // Tìm PersonalTrainer từ account_id
            PersonalTrainer personalTrainer = personalTrainerService.findByAccount(account);
            if (personalTrainer == null) {
                return ResponseEntity.status(404).body("Không tìm thấy PersonalTrainer cho account_id: " + account.getAccountId());
            }

            // Cập nhật thông tin Personal Trainer
            personalTrainer.setFirstName(updatedTrainer.getFirstName());
            personalTrainer.setLastName(updatedTrainer.getLastName());
            personalTrainer.setGender(updatedTrainer.getGender());
            personalTrainer.setBirthDate(updatedTrainer.getBirthDate());
            personalTrainer.setPhone(updatedTrainer.getPhone());
            personalTrainer.setAddress(updatedTrainer.getAddress());
            personalTrainer.setEmail(updatedTrainer.getEmail());
            personalTrainer.setDegree(updatedTrainer.getDegree());

            // Lưu thay đổi
            personalTrainerService.updatePersonalTrainer(personalTrainer);

            return ResponseEntity.ok("Cập nhật thông tin Personal Trainer thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
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