package ie.atu.arsenalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/players")
    public class PlayerController {

        @Autowired
        private PlayerService playerService;

        @GetMapping
        public List<Player> getAllPlayers() {
            return playerService.getAllPlayers;

        }
