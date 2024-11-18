package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateExerciseDTO;
import com.example.springmvc.DTO_Class.CreateProgramDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.ProgramsRepository.ExerciseOfProgramRepository;
import com.example.springmvc.dao.ProgramsRepository.ExerciseRepository;
import com.example.springmvc.dao.ProgramsRepository.ProgramRepository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.entity.Program.ExerciseOfProgram;
import com.example.springmvc.entity.Program.Program;
import com.example.springmvc.service.class_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programs")
@CrossOrigin(origins = "http://localhost:3000")

public class ProgramController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ClientRespository clientRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseOfProgramRepository exerciseOfProgramRepository;


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

    @GetMapping("/get/{programId}")
    public ResponseEntity<Map<String, Object>> getProgramById(@PathVariable int programId) {
        // Retrieve the program by ID
        Optional<Program> programOpt = programRepository.findById(programId);
        if (programOpt.isEmpty()) {
            // Program not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Program program = programOpt.get();

        // Structure the response data for the program
        Map<String, Object> programResponse = new HashMap<>();
        programResponse.put("program_id", program.getProgramId());
        programResponse.put("clientName", program.getClient().getFirstName());
        programResponse.put("day", program.getDay());
        programResponse.put("week", program.getWeek());

        // Initialize a list to hold exercises in the program
        List<Map<String, Object>> exercises = new ArrayList<>();

        // Loop through each ExerciseOfProgram item in the program
        for (ExerciseOfProgram exerciseOfProgram : program.getExerciseOfPrograms()) {
            Map<String, Object> exercise = new HashMap<>();
            exercise.put("exerciseName", exerciseOfProgram.getExercise().getExercisename());
            exercise.put("setsStandard", exerciseOfProgram.getSetsStandard());
            exercise.put("repsStandard", exerciseOfProgram.getRepsStandard());
            exercise.put("set1", exerciseOfProgram.getSet1());
            exercise.put("set2", exerciseOfProgram.getSet2());
            exercise.put("set3", exerciseOfProgram.getSet3());
            exercise.put("set4", exerciseOfProgram.getSet4());
            exercise.put("set5", exerciseOfProgram.getSet5());
            exercise.put("tempo", exerciseOfProgram.getTempo());
            exercise.put("rirRpe", exerciseOfProgram.getRirRpe());
            exercise.put("loadOfExercise", exerciseOfProgram.getLoadOfExercise());

            // Add the exercise to the list
            exercises.add(exercise);
        }

        // Add the exercises to the program response
        programResponse.put("exercises", exercises);

        // Return the program response
        return ResponseEntity.ok(programResponse);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Map<String, Object>>> getAllPrograms() {
        // Retrieve all programs from the database
        List<Program> programs = programRepository.findAll();

        // Initialize a list to hold the response for each program
        List<Map<String, Object>> programResponses = new ArrayList<>();

        // Loop through each program and structure the response
        for (Program program : programs) {
            Map<String, Object> programResponse = new HashMap<>();
            programResponse.put("program_id", program.getProgramId());
            programResponse.put("clientName", program.getClient().getFirstName());
            programResponse.put("day", program.getDay());
            programResponse.put("week", program.getWeek());

            // Initialize a list to hold exercises in the program
            List<Map<String, Object>> exercises = new ArrayList<>();

            // Loop through each ExerciseOfProgram item in the program
            for (ExerciseOfProgram exerciseOfProgram : program.getExerciseOfPrograms()) {
                Map<String, Object> exercise = new HashMap<>();
                exercise.put("exerciseName", exerciseOfProgram.getExercise().getExercisename());
                exercise.put("setsStandard", exerciseOfProgram.getSetsStandard());
                exercise.put("repsStandard", exerciseOfProgram.getRepsStandard());
                exercise.put("set1", exerciseOfProgram.getSet1());
                exercise.put("set2", exerciseOfProgram.getSet2());
                exercise.put("set3", exerciseOfProgram.getSet3());
                exercise.put("set4", exerciseOfProgram.getSet4());
                exercise.put("set5", exerciseOfProgram.getSet5());
                exercise.put("tempo", exerciseOfProgram.getTempo());
                exercise.put("rirRpe", exerciseOfProgram.getRirRpe());
                exercise.put("loadOfExercise", exerciseOfProgram.getLoadOfExercise());

                // Add the exercise to the list
                exercises.add(exercise);
            }

            // Add the exercises to the program response
            programResponse.put("exercises", exercises);

            // Add the program response to the list of programs
            programResponses.add(programResponse);
        }

        // Return the list of programs
        return ResponseEntity.ok(programResponses);
    }

    @GetMapping("/api/programs/pending")
    public ResponseEntity<List<Object[]>> getPendingPrograms() {
        List<Object[]> pendingPrograms = notificationService.getPendingPrograms();
        return ResponseEntity.ok(pendingPrograms);
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
            Optional<Exercise> exercise = exerciseRepository.findByExerciseName(exerciseName);
            if (exercise.isEmpty()) {
                return ResponseEntity.badRequest().body("Exercise not found: " + exerciseName);
            }

            ExerciseOfProgram exerciseOfProgram = new ExerciseOfProgram();
            exerciseOfProgram.setProgram(savedProgram);
            exerciseOfProgram.setExercises(exercise.orElse(null));
            exerciseOfProgram.setSetsStandard(exerciseDTO.getSetsStandard());
            exerciseOfProgram.setRepsStandard(exerciseDTO.getRepsStandard());
            exerciseOfProgram.setSet1(exerciseDTO.getSet1());
            exerciseOfProgram.setSet2(exerciseDTO.getSet2());
            exerciseOfProgram.setSet3(exerciseDTO.getSet3());
            exerciseOfProgram.setSet4(exerciseDTO.getSet4());
            exerciseOfProgram.setSet5(exerciseDTO.getSet5());
            exerciseOfProgram.setTempo(exerciseDTO.getTempo());
            exerciseOfProgram.setRirRpe(exerciseDTO.getRirRpe());
            exerciseOfProgram.setLoadOfExercise(exerciseDTO.getLoad());
            // Save each ExerciseOfProgram instance
            exerciseOfProgramRepository.save(exerciseOfProgram);
        }

        return ResponseEntity.ok("Program created successfully with exercises");
    }

    @PutMapping("/update/program/{programId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> updateProgramAndExercise(
            @PathVariable int programId,
            @RequestBody CreateProgramDTO updateDTO) {

        // Tìm `Program` theo `programId`
        Optional<Program> existingProgramOpt = programRepository.findById(programId);
        if (existingProgramOpt.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Program existingProgram = existingProgramOpt.get();

        // Cập nhật thông tin cơ bản của `Program`
        existingProgram.setDay(updateDTO.getDay());
        existingProgram.setWeek(updateDTO.getWeek());

        // Tạo map các `ExerciseOfProgram` dựa trên `exerciseName`
        Map<String, ExerciseOfProgram> existingExerciseMap = existingProgram.getExerciseOfPrograms().stream()
                .collect(Collectors.toMap(e -> e.getExercise().getExercisename(), e -> e));

        // Danh sách lưu các `ExerciseOfProgram` đã cập nhật
        List<ExerciseOfProgram> updatedExercises = new ArrayList<>();

        // Vòng lặp qua từng bài tập trong DTO để cập nhật
        for (CreateExerciseDTO exerciseDTO : updateDTO.getExercises()) {
            String exerciseName = exerciseDTO.getSelectedExerciseName();

            // Chỉ cập nhật nếu bài tập tồn tại trong `Program`
            if (existingExerciseMap.containsKey(exerciseName)) {
                ExerciseOfProgram exerciseOfProgram = existingExerciseMap.get(exerciseName);

                // Cập nhật chi tiết bài tập từ DTO
                exerciseOfProgram.setSetsStandard(exerciseDTO.getSetsStandard());
                exerciseOfProgram.setRepsStandard(exerciseDTO.getRepsStandard());
                exerciseOfProgram.setSet1(exerciseDTO.getSet1());
                exerciseOfProgram.setSet2(exerciseDTO.getSet2());
                exerciseOfProgram.setSet3(exerciseDTO.getSet3());
                exerciseOfProgram.setSet4(exerciseDTO.getSet4());
                exerciseOfProgram.setSet5(exerciseDTO.getSet5());
                exerciseOfProgram.setTempo(exerciseDTO.getTempo());
                exerciseOfProgram.setRirRpe(exerciseDTO.getRirRpe());
                exerciseOfProgram.setLoadOfExercise(exerciseDTO.getLoad());

                // Thêm `exerciseOfProgram` đã cập nhật vào danh sách
                updatedExercises.add(exerciseOfProgram);
            }
        }

        // Cập nhật danh sách `ExerciseOfProgram` trong `Program`
        existingProgram.setExerciseOfPrograms(updatedExercises);

        // Lưu `Program` đã cập nhật
        existingProgram = programRepository.save(existingProgram);

        // Trả về phản hồi thành công
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Program and exercises updated successfully");
        successResponse.put("program", existingProgram);

        return ResponseEntity.ok(successResponse);
    }


    @PutMapping("/update/program/{programId}/exercise/{exerciseId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> updateProgramAndExercise(
            @PathVariable int programId,
            @PathVariable int exerciseId,
            @RequestBody CreateProgramDTO updateDTO) {

        // Find the existing program by ID
        Optional<Program> existingProgramOpt = programRepository.findById(programId);
        if (existingProgramOpt.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Program existingProgram = existingProgramOpt.get();

        // Update basic properties of the program
        existingProgram.setDay(updateDTO.getDay());
        existingProgram.setWeek(updateDTO.getWeek());

        // Create a map of existing exercises by name for easy lookup
        Map<String, ExerciseOfProgram> existingExerciseMap = existingProgram.getExerciseOfPrograms().stream()
                .collect(Collectors.toMap(e -> e.getExercise().getExercisename(), e -> e));

        // List to store updated ExerciseOfProgram items
        List<ExerciseOfProgram> updatedExercises = new ArrayList<>();

        // Loop through each exercise item in the request DTO
        for (CreateExerciseDTO exerciseDTO : updateDTO.getExercises()) {
            String exerciseName = exerciseDTO.getSelectedExerciseName();

            // Only update if the exercise exists in the existing program
            if (existingExerciseMap.containsKey(exerciseName)) {
                ExerciseOfProgram exerciseOfProgram = existingExerciseMap.get(exerciseName);

                // Update properties based on the DTO
                exerciseOfProgram.setSetsStandard(exerciseDTO.getSetsStandard());
                exerciseOfProgram.setRepsStandard(exerciseDTO.getRepsStandard());
                exerciseOfProgram.setSet1(exerciseDTO.getSet1());
                exerciseOfProgram.setSet2(exerciseDTO.getSet2());
                exerciseOfProgram.setSet3(exerciseDTO.getSet3());
                exerciseOfProgram.setSet4(exerciseDTO.getSet4());
                exerciseOfProgram.setSet5(exerciseDTO.getSet5());
                exerciseOfProgram.setTempo(exerciseDTO.getTempo());
                exerciseOfProgram.setRirRpe(exerciseDTO.getRirRpe());
                exerciseOfProgram.setLoadOfExercise(exerciseDTO.getLoad());

                // Add the updated exerciseOfProgram to the list
                updatedExercises.add(exerciseOfProgram);
            }
        }

        // Set only the updated list of ExerciseOfProgram items (no additions)
        existingProgram.setExerciseOfPrograms(updatedExercises);

        // Save the updated program
        existingProgram = programRepository.save(existingProgram);

        // Return success response
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Program and exercises updated successfully");
        successResponse.put("program", existingProgram);

        return ResponseEntity.ok(successResponse);
    }


    @DeleteMapping("/delete/{programId}")
    public ResponseEntity<Map<String, Object>> deleteProgramById(@PathVariable int programId) {
        // Check if the program exists
        Optional<Program> programOpt = programRepository.findById(programId);
        if (programOpt.isEmpty()) {
            // Program not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Program not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Delete the program
        programRepository.deleteById(programId);

        // Prepare response after successful deletion
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Program deleted successfully");
        response.put("programId", programId);

        // Return a success response
        return ResponseEntity.ok(response);
    }

}

