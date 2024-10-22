package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateProgramDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.ProgramsRepository.ExerciseOfPrgramRepository;
import com.example.springmvc.dao.ProgramsRepository.ExerciseRepository;
import com.example.springmvc.dao.ProgramsRepository.ProgramRepository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.entity.Program.ExerciseOfProgram;
import com.example.springmvc.entity.Program.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ClientRespository clientRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseOfPrgramRepository exerciseOfProgramRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createProgram(@RequestBody CreateProgramDTO createProgramDTO) {
        // Retrieve the client by clientName
        Client client = clientRepository.findByFirstName(createProgramDTO.getClientName());
        if (client == null) {
            return ResponseEntity.badRequest().body("Client not found");
        }

        // Create a new Program instance
        Program program = new Program();
        program.setClient(client);
        program.setDay(createProgramDTO.getDay());
        program.setWeek(createProgramDTO.getWeek());

        // Save the program
        Program savedProgram = programRepository.save(program);

        // Create ExerciseOfProgram instances based on the provided exercises
        String exerciseName = createProgramDTO.getSelectedExerciseName();
        Exercise exercise = exerciseRepository.findByExerciseName(exerciseName);
        if (exercise == null) {
            return ResponseEntity.badRequest().body("Exercise not found: " + exerciseName);
        }
            ExerciseOfProgram exerciseOfProgram = new ExerciseOfProgram();
            exerciseOfProgram.setProgram(savedProgram);
            exerciseOfProgram.setExercises(exercise);
            exerciseOfProgram.setSetsStandard(createProgramDTO.getSetsStandard());
            exerciseOfProgram.setRepsStandard(createProgramDTO.getRepsStandard());
            exerciseOfProgram.setSet1(createProgramDTO.getSet1());
            exerciseOfProgram.setSet2(createProgramDTO.getSet2());
            exerciseOfProgram.setSet3(createProgramDTO.getSet3());
            exerciseOfProgram.setSet4(createProgramDTO.getSet4());
            exerciseOfProgram.setSet5(createProgramDTO.getSet5());
            exerciseOfProgram.setTempo(createProgramDTO.getTempo());
            exerciseOfProgram.setRirRpe(createProgramDTO.getRirRpe());

            // Save ExerciseOfProgram
            exerciseOfProgramRepository.save(exerciseOfProgram);

        return ResponseEntity.ok("Program created successfully");
    }
}
