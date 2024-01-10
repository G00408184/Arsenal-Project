package ie.atu.arsenalproject.playerMicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    // Use IgnoreCase to make the query case-insensitive
    Player findByNameIgnoreCase(String name);
}

