package com.example.springmvc.rest;

import com.example.springmvc.entity.Login.Account;
import com.example.springmvc.service.interface_service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/getAllAccount")
    public List<Account> getAllAccount(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<Optional<Account>> getAccountByUserName(@PathVariable int id){
        Optional<Account> account = accountService.getAccountById(id);
        if (account.isPresent()){
            return ResponseEntity.ok(account);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAccountByName")
    public ResponseEntity<Account> getAccountByUserName(@PathVariable String username){
        Account account = accountService.getAccountByUserName(username);
        if (account != null){
            return ResponseEntity.ok(account);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        account.setAccountId(0); // bat buoc them moi va phat sinh ra id
        account = accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Account>> updateAccount(@PathVariable int id, @RequestBody Account account){
        Optional<Account> existingAccounts = accountService.getAccountById(id);
        if (!existingAccounts.isEmpty()){
            existingAccounts.get().setPassword(account.getPassword());
            accountService.updateAccount(account);
            return ResponseEntity.ok(existingAccounts);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteAccount(@PathVariable int id){
        Optional<Account> existingAccount = accountService.getAccountById(id);
        if (existingAccount.isPresent()){
            accountService.deleteAccountById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
