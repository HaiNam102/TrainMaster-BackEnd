package com.example.springmvc.dao.ActorRespository;//package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Program.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRespository extends JpaRepository<Client,Integer> {
    public Client findByFirstName(String name);

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> getClientByEmail(@Param("email") String email);

    @Query("SELECT c.client_id, c.firstName, c.lastName, c.gender, c.birthDate, c.phone, c.address, c.email, c.years_training, " +
            "c.blood_glucose, c.blood_pressure, c.job " +
            "FROM Client c " +
            "JOIN c.account a " +
            "WHERE a.username = :username")
    List<Object[]> findClientByUsername(@Param("username") String username);

}
