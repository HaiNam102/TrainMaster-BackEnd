package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.clienttracking.ClientsTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsTrackingRespository extends JpaRepository<ClientsTracking, Integer> {
    List<ClientsTracking> findByClient(Client client);
    @Query("SELECT ct FROM ClientsTracking ct " +
            "JOIN ct.client c " +
            "JOIN c.account a " +
            "WHERE a.username = :username")
    List<ClientsTracking> findAllByUsername(@Param("username") String username);
}

