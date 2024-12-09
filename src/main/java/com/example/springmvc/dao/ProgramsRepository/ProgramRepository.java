package com.example.springmvc.dao.ProgramsRepository;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.MealPlan;
import com.example.springmvc.entity.Program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Integer> {
    List<Program> findByClient(Client client);

    @Query("SELECT p.programId, p.day, p.week, e.exerciseName, ep.setsStandard, " +
            "ep.repsStandard, ep.set1, ep.set2, ep.set3, ep.set4, ep.set5, " +
            "ep.tempo, ep.rirRpe, ep.loadOfExercise " +
            "FROM Program p " +
            "JOIN FeedbackNotification fn ON p = fn.program " +
            "JOIN ExerciseOfProgram ep ON p = ep.program " +
            "JOIN Exercise e ON ep.exercise = e " +
            "WHERE fn.approve = false")
    List<Object[]> findPendingPrograms();


    @Query("SELECT p.programId, p.day, p.week, e.exerciseName, ep.setsStandard, " +
            "SUM(COALESCE(ep.set1, 0) + COALESCE(ep.set2, 0) + COALESCE(ep.set3, 0) + COALESCE(ep.set4, 0) + COALESCE(ep.set5, 0)) OVER (PARTITION BY p.programId) AS totalVolume, " +
            "ep.repsStandard, ep.set1, ep.set2, ep.set3, ep.set4, ep.set5, " +
            "ep.tempo, ep.rirRpe, ep.loadOfExercise, " +
            "COALESCE(ep.set1, 0) + COALESCE(ep.set2, 0) + COALESCE(ep.set3, 0) + COALESCE(ep.set4, 0) + COALESCE(ep.set5, 0) AS volume " +
            "FROM Program p " +
            "JOIN FeedbackNotification fn ON p = fn.program " +
            "JOIN ExerciseOfProgram ep ON p = ep.program " +
            "JOIN Exercise e ON ep.exercise = e " +
            "JOIN p.client c " +
            "JOIN c.account a " +
            "WHERE fn.approve = true AND a.username = :username")
    List<Object[]> findApprovedProgramsByUsername(@Param("username") String username);


}
