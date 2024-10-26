package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateExerciseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/client/{clientName}")
    public ResponseEntity<Map<String, Object>> getProgramByClientName(@PathVariable String clientName) {
        // Tìm Client theo tên
        Client client = clientRepository.findByFirstName(clientName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Tìm tất cả MealPlans liên quan đến Client này
        List<Program> programs = programRepository.findByClient(client);

        // Tạo một Map để lưu kết quả phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("clientName", client.getFirstName());
        response.put("program", programs);

        return ResponseEntity.ok(response);
    }

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

        // Create ExerciseOfProgram instances for each exercise
        for (CreateExerciseDTO exerciseDTO : createProgramDTO.getExercises()) {
            String exerciseName = exerciseDTO.getSelectedExerciseName();
            Exercise exercise = exerciseRepository.findByExerciseName(exerciseName);
            if (exercise == null) {
                return ResponseEntity.badRequest().body("Exercise not found: " + exerciseName);
            }

            ExerciseOfProgram exerciseOfProgram = new ExerciseOfProgram();
            exerciseOfProgram.setProgram(savedProgram);
            exerciseOfProgram.setExercises(exercise);
            exerciseOfProgram.setSetsStandard(exerciseDTO.getSetsStandard());
            exerciseOfProgram.setRepsStandard(exerciseDTO.getRepsStandard());
            exerciseOfProgram.setSet1(exerciseDTO.getSet1());
            exerciseOfProgram.setSet2(exerciseDTO.getSet2());
            exerciseOfProgram.setSet3(exerciseDTO.getSet3());
            exerciseOfProgram.setSet4(exerciseDTO.getSet4());
            exerciseOfProgram.setSet5(exerciseDTO.getSet5());
            exerciseOfProgram.setTempo(exerciseDTO.getTempo());
            exerciseOfProgram.setRirRpe(exerciseDTO.getRirRpe());

            // Save each ExerciseOfProgram instance
            exerciseOfProgramRepository.save(exerciseOfProgram);
        }

        return ResponseEntity.ok("Program created successfully with exercises");
    }

}
