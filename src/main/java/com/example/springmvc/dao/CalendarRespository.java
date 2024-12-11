package com.example.springmvc.dao;

import com.example.springmvc.DTO_Class.CreateCalendarDTO;
import com.example.springmvc.entity.Calendar;
import com.example.springmvc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRespository extends JpaRepository<Calendar, Integer> {
    List<Calendar> findByClient(Client client);
    List<Calendar> findAll();
    public Optional<Calendar> findByCalendarId(int calendarId);
    @Query("SELECT new com.example.springmvc.DTO_Class.CreateCalendarDTO(ca.calendarId, ca.attendanceStatus, ca.date, ca.timestart, ca.timeend) " +
            "FROM Calendar ca " +
            "JOIN ca.client c " +
            "JOIN c.account a " +
            "WHERE a.username = :username")
    List<CreateCalendarDTO> findApprovedCalendarByUsername(@Param("username") String username);

}

