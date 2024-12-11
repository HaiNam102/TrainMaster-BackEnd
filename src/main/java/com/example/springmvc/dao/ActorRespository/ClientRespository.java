package com.example.springmvc.dao.ActorRespository;//package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Program.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRespository extends JpaRepository<Client,Integer> {
    public Client findByFirstName(String name);

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> getClientByEmail(@Param("email") String email);


}
