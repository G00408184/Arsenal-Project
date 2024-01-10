package ie.atu.arsenalproject.trainingSessionMicroservice;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepo extends JpaRepository<TrainingSession, Long> {
    // Custom query methods if needed
}