package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
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
    @Autowired
    private PlatformRepository platformRepo;
    @Autowired
    private DeveloperRepository devRepo;

//    TODO: extract methods body to service
//    @Autowired
//    private GameService gameService;

    @GetMapping("/game")
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
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) {
        if (gameRepo.existsByTitle(game.getTitle())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gameRepo.save(game);
        return new ResponseEntity<>(gameRepo.findByTitle(game.getTitle()), HttpStatus.OK);
    }

    @PostMapping(value = "game/{id}/add/platform")
    public ResponseEntity<List<Platform>> addPlatform(@Valid @RequestBody Platform platform, @PathVariable("id") long id) {
        if (!gameRepo.exists(id) || !platformRepo.existsByName(platform.getName())) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Game game = gameRepo.findById(id);
        List<Platform> list = game.getPlatforms();
        if (list.contains(platform)) {
            return new ResponseEntity<>(null ,HttpStatus.NOT_FOUND);
        }
        list.add(platform);
        game.setPlatforms(list);
        gameRepo.save(game);
        return new ResponseEntity<>(game.getPlatforms() ,HttpStatus.OK);
    }

    @PutMapping("game/{id}")
    public ResponseEntity<Game> editGame(@Valid @RequestBody Game game, @PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Game oldGame = gameRepo.findById(id);
        game.setId(id);
        game.setPlatforms(oldGame.getPlatforms());
        game.setDeveloper(oldGame.getDeveloper());
        gameRepo.save(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("game/{id}/platforms")
    public ResponseEntity<Game> editGamePlatforms(@Valid @RequestBody List<Platform> platforms, @PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (Platform p : platforms) {
            if (!platformRepo.existsByName(p.getName())) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }

        Game game = gameRepo.findById(id);
        game.setPlatforms(platforms);
        gameRepo.save(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("game/{id}/developer")
    public ResponseEntity<Game> editDeveloper(@Valid @RequestBody Developer developer, @PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (!devRepo.existsByName(developer.getName())) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Game game = gameRepo.findById(id);
        game.setDeveloper(devRepo.findByName(developer.getName()));
        gameRepo.save(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping("game/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable("id") long id) {
        if (!gameRepo.exists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Game game = gameRepo.findById(id);

        gameRepo.delete(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
