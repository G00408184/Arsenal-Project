package ie.atu.arsenalproject.trainingSessionMicroservice;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


    @Service
    public class TrainingSessionService {
        private final TrainingSessionRepo trainingSessionRepo;

        public TrainingSessionService(TrainingSessionRepo trainingSessionRepo) {
            this.trainingSessionRepo = trainingSessionRepo;
        }

        public TrainingSession getTrainingSessionById(Long id) {
            return trainingSessionRepo.findById(id).orElse(null);
        }

        public List<TrainingSession> getAllTrainingSessions() {
            return trainingSessionRepo.findAll();
        }

        public void saveTrainingSession(TrainingSession trainingSession) {
            trainingSessionRepo.save(trainingSession);
        }

        public void deleteTrainingSession(TrainingSession trainingSession) {
            trainingSessionRepo.delete(trainingSession);
        }

        @Transactional
        public TrainingSession updateTrainingSessionById(Long id, TrainingSession updatedTrainingSession) {
            TrainingSession trainingSession = getTrainingSessionById(id);
            if (trainingSession != null) {
                // Update the existing training session with the new details
                trainingSession.setTitle(updatedTrainingSession.getTitle());
                trainingSession.setDescription(updatedTrainingSession.getDescription());
                trainingSession.setScheduledTime(updatedTrainingSession.getScheduledTime());
                trainingSession.setLocation(updatedTrainingSession.getLocation());
                // Any other fields that need updating can be set here
                saveTrainingSession(trainingSession);
                return trainingSession;
            }
            // Handling the case where the training session is not found
            // You could throw an exception or return null depending on your error handling strategy
            return null;
        }
    }



