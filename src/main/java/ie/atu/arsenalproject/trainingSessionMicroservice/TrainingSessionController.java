package ie.atu.arsenalproject.trainingSessionMicroservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingSessions")
public class TrainingSessionController {
    private final TrainingSessionService trainingSessionService;

    public TrainingSessionController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainingSession(@PathVariable Long id) {
        TrainingSession trainingSession = trainingSessionService.getTrainingSessionById(id);
        if (trainingSession == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainingSession);
    }

    @GetMapping
    public ResponseEntity<List<TrainingSession>> getAllTrainingSessions() {
        return ResponseEntity.ok(trainingSessionService.getAllTrainingSessions());
    }

    @PostMapping
    public ResponseEntity<String> createTrainingSession(@RequestBody TrainingSession trainingSession) {
        trainingSessionService.saveTrainingSession(trainingSession);
        return new ResponseEntity<>("Training session created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainingSession(@PathVariable Long id) {
        TrainingSession trainingSession = trainingSessionService.getTrainingSessionById(id);
        if (trainingSession == null) {
            return ResponseEntity.notFound().build();
        }
        trainingSessionService.deleteTrainingSession(trainingSession);
        return new ResponseEntity<>("Training session deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrainingSession(@PathVariable Long id, @RequestBody TrainingSession updatedTrainingSession) {
        TrainingSession trainingSession = trainingSessionService.updateTrainingSessionById(id, updatedTrainingSession);

        if (trainingSession == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>("Training session updated successfully", HttpStatus.OK);
    }

}