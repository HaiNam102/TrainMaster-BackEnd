package com.example.springmvc.dao;

import com.example.springmvc.entity.Login.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRespository extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
}
