package ie.atu.arsenalproject.playerMicroservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Transactional
    public void savePlayer(Player player){
        playerRepo.save(player);
    }

    public Player getPlayerByName(String name){
        return playerRepo.findByNameIgnoreCase(name);
    }

    public List<Player> getAllPlayers(){
        return playerRepo.findAll();
    }

    public void deletePlayer(Player player) {
        playerRepo.delete(player);
    }

    @Transactional
    public Player updatePlayerByName(String name, Player updatedPlayer) {
        Player player = getPlayerByName(name);
        if (player != null) {
            player.setName(updatedPlayer.getName());
            player.setAge(updatedPlayer.getAge());
            player.setEmail(updatedPlayer.getEmail());
            player.setNationality(updatedPlayer.getNationality());
            player.setPosition(updatedPlayer.getPosition());
            // Any other fields that need updating can be set here
            savePlayer(player);
            return player;
        }
        // Handling the case where the player is not found
        // You could throw an exception or return null depending on your error handling strategy
        return null;
    }
}
