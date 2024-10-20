package com.example.springmvc.dao;

import com.example.springmvc.entity.Login.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRespository extends JpaRepository<Account,Integer> {
//    public Account findByUserName(String username);
}
