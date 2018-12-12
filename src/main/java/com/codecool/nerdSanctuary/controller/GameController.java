package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    @ResponseBody
    public List<Game> getAllGames() {
        return gameService.getGames();
    }

    @GetMapping("/game/{id}")
    @ResponseBody
    public ResponseEntity<Game> getById(@PathVariable("id") long id) {
        Game game = gameService.getGame(id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @GetMapping("/game/{id}/platforms")
    @ResponseBody
    public ResponseEntity<List<Platform>> getPlatforms(@PathVariable("id") long id) {
        List<Platform> platforms = gameService.getPlatforms(id);
        HttpStatus status;

        if (platforms == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(platforms, status);
    }

    @GetMapping("/game/{id}/developer")
    @ResponseBody
    public ResponseEntity<Developer> getDeveloper(@PathVariable("id") long id) {
        Developer developer = gameService.getDeveloper(id);
        HttpStatus status;

        if (developer == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(developer, status);
    }

    @PostMapping(value = "game/add")
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game newGame) {
        Game game = gameService.addGame(newGame);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @PostMapping(value = "game/{id}/add/platform")
    public ResponseEntity<Game> addPlatform(@Valid @RequestBody Platform platform, @PathVariable("id") long id) {
        Game game = gameService.addPlatform(platform, id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @PutMapping("game/{id}")
    public ResponseEntity<Game> editGame(@Valid @RequestBody Game newGame, @PathVariable("id") long id) {
        Game game = gameService.editGame(newGame, id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @PutMapping("game/{id}/platforms")
    public ResponseEntity<Game> editGamePlatforms(@Valid @RequestBody List<Platform> platforms, @PathVariable("id") long id) {
        Game game = gameService.editGamePlatforms(platforms, id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @PutMapping("game/{id}/developer")
    public ResponseEntity<Game> editDeveloper(@Valid @RequestBody Developer developer, @PathVariable("id") long id) {
        Game game = gameService.editDeveloper(developer, id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }

    @DeleteMapping("game/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable("id") long id) {
        Game game = gameService.deleteGame(id);
        HttpStatus status;

        if (game == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(game, status);
    }
}
