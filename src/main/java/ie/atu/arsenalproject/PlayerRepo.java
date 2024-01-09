package ie.atu.arsenalproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findByName(String name);

    Player findById(String id);
}
