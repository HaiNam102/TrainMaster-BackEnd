package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.AccountRespository;
import com.example.springmvc.entity.Login.Account;
//import com.example.springmvc.entity.Client;
import com.example.springmvc.service.interface_service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRespository accountRespository;

    @Autowired
    public AccountServiceImpl(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRespository.findAll();
    }

    @Override
    @Transactional
    public Account addAccount(Account account) {
        return this.accountRespository.save(account);
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return this.accountRespository.saveAndFlush(account);
    }

    @Override
    @Transactional
    public Account deleteAccountById(int id) {
        this.accountRespository.deleteById(id);
        return null;
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return this.accountRespository.findById(id);
    }
}
