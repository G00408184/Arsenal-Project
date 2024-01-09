package ie.atu.arsenalproject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    public class PlayerController {
        private final PlayerService playerService;
        public PlayerController(PlayerService playerService) {this.playerService = playerService;}

        @GetMapping("/{Id}")
        public ResponseEntity<?> getPlayer(@PathVariable String id){
            if(id.length()>5 || id.isBlank()){
                return ResponseEntity.badRequest().body("Id is Invalid");
            }
            Player player = playerService.getPlayerById(id);
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

        }


