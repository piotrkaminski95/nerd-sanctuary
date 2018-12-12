package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepo;

//    TODO: extract methods body to service
//    @Autowired
//    private GameService gameService;

    @GetMapping
    @ResponseBody
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/game/{id}")
    @ResponseBody
    public ResponseEntity<Game> getById(@PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameRepo.findById(id), HttpStatus.OK);
    }

    @GetMapping("/game/{id}/platforms")
    @ResponseBody
    public ResponseEntity<List<Platform>> getPlatforms(@PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Game game = gameRepo.findById(id);
        return new ResponseEntity<>(game.getPlatforms(), HttpStatus.OK);
    }

    @GetMapping("/game/{id}/developer")
    @ResponseBody
    public ResponseEntity<Developer> getDeveloper(@PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Game game = gameRepo.findById(id);
        return new ResponseEntity<>(game.getDeveloper(), HttpStatus.OK);
    }

    @PostMapping(value = "game/add")
    public ResponseEntity<?> addGame(@Valid @RequestBody Game game) {
        if (gameRepo.existsByTitle(game.getTitle())) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        gameRepo.save(game);
        return new ResponseEntity(HttpStatus.OK);
    }
}
