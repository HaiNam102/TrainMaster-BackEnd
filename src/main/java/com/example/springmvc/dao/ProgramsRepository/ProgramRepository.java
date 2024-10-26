package com.example.springmvc.dao.ProgramsRepository;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.MealPlan;
import com.example.springmvc.entity.Program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Integer> {
    List<Program> findByClient(Client client);
}
