package ie.atu.arsenalproject;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }


    public void savePlayer(Player player){
        playerRepo.save(player);
   }

    public Player getPlayerByName(String name){
       return playerRepo.findByName(name);
    }


    public List<Player> getAllPlayers(){
        return playerRepo.findAll();
    }
    public void deletePlayer(Player player) {
        playerRepo.delete(player);
    }
}

