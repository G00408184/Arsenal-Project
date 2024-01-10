package ie.atu.arsenalproject.playerMicroservice;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    public class PlayerController {
        private final PlayerService playerService;
        public PlayerController(PlayerService playerService) {this.playerService = playerService;}

        @GetMapping("/{name}")
        public ResponseEntity<?> getPlayer(@PathVariable String name){

            Player player = playerService.getPlayerByName(name);
            if (player == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(player);
        }

        @GetMapping("/findAllPlayers")
        public ResponseEntity<?> getAllPlayers(){
            List<Player> players = playerService.getAllPlayers();
            if (players == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(players);
        }

        @PostMapping("/createPlayer")
        public ResponseEntity<String>create(@Valid @RequestBody Player player){
            playerService.savePlayer(player);
            return new ResponseEntity<>("Player created successfully", HttpStatus.OK);
        }
        @DeleteMapping("/delete/{name}")
        public ResponseEntity<String> deletePlayerByName(@PathVariable String name) {
            // Check if the name is not empty
            if (name.isBlank()) {
                return ResponseEntity.badRequest().body("Player name cannot be empty");
            }

            List<Player> players = playerService.getAllPlayers();
            Player existingPlayer = null;
            // Iterate through the list of players and find a case-insensitive match
            for (Player player : players) {
                if (player.getName().equalsIgnoreCase(name)) {
                    existingPlayer = player;
                    break; // Exit the loop as soon as a match is found
                }
            }
            if (existingPlayer == null) {
                return ResponseEntity.notFound().build();
            }
            // Delete the player from the repository
            playerService.deletePlayer(existingPlayer);
            return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
        }

        @PutMapping("/edit/{name}")
        public ResponseEntity<String> editPlayerByName(@PathVariable String name, @Valid @RequestBody Player updatedPlayer) {
            Player existingPlayer = playerService.updatePlayerByName(name, updatedPlayer);

            if (existingPlayer == null) {
                return ResponseEntity.notFound().build();
            }

            return new ResponseEntity<>("Player updated successfully", HttpStatus.OK);
        }



    }


