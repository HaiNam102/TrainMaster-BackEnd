package com.example.springmvc.dao;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRespository extends JpaRepository<MealPlan,Integer> {
    List<MealPlan> findByClient(Client client);
}
