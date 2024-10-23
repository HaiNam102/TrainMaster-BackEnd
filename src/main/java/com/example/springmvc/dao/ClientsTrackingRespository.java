package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.ClientsTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsTrackingRespository extends JpaRepository<ClientsTracking, Integer> {
    List<ClientsTracking> findByClient(Client client);
}

