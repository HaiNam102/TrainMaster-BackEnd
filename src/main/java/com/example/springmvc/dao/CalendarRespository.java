package com.example.springmvc.dao;

import com.example.springmvc.entity.Calendar;
import com.example.springmvc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRespository extends JpaRepository<Calendar,Integer> {
    List<Calendar> findByClient(Client client);
}
