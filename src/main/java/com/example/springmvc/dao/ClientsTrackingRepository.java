package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Tracking.ClientsTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsTrackingRepository extends JpaRepository<ClientsTracking,Integer> {
    public List<ClientsTracking> findByClient(Client client);
}
