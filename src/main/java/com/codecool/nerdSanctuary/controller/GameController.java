package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private GameService gameService;

    @GetMapping
    @ResponseBody
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/game/{id}")
    @ResponseBody
    public Game getById(@PathVariable("id") long id) {
        return gameRepo.findOne(id);
    }

    @PostMapping(value = "game/addgame")
    public ResponseEntity<?> addGame(@RequestBody HashMap<String, Object> gameMap) {
        return gameService.addGame(gameMap);
    }
}
