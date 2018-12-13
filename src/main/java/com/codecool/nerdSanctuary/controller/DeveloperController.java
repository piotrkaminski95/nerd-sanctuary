package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    // i don't know but i have idea to send here some graphical content it will be nicer
//    @GetMapping("/developer/all")
    @GetMapping("/developer")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return new ResponseEntity<>(developerService.getAllDeveloper(), HttpStatus.OK);
    }

    @GetMapping("/developer/{id}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable long id) {
        return new ResponseEntity<>(developerService.getDeveloper(id), HttpStatus.OK);
    }

    @GetMapping("/developer/{id}/games")
    public ResponseEntity<List<Game>> getDeveloperGames(@PathVariable long id) {
        return new ResponseEntity<>(developerService.getDeveloperGames(id), HttpStatus.OK);
    }

    @GetMapping("/developer/{devId}/games/{gameId}")
    public ResponseEntity<Game> getDeveloperGame(@PathVariable long devId, @PathVariable long gameId) {
        return new ResponseEntity<>(developerService.getDeveloperGame(devId, gameId), HttpStatus.OK);
    }

    @PostMapping("/developer")
    public ResponseEntity<Developer> postDeveloper(@Valid @RequestBody Developer developer) {
        return new ResponseEntity<>(developerService.saveDeveloper(developer), HttpStatus.CREATED);
    }

    @PostMapping("/developer/{id}/games")
    public ResponseEntity<List<Game>> postDeveloperGame(@PathVariable long id, @Valid @RequestBody Game game) {
        return new ResponseEntity<>(developerService.addDeveloperGame(game, id), HttpStatus.CREATED);
    }

    @PutMapping("/developer/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable long id, @Valid @RequestBody Developer updatedDev) {
        return new ResponseEntity<>(developerService.updateDeveloper(id, updatedDev), HttpStatus.OK);
    }

    @DeleteMapping("/developer/{id}")
    public ResponseEntity<List<Developer>> deleteDeveloper(@PathVariable long id) {
        return new ResponseEntity<>(developerService.deleteDeveloper(id), HttpStatus.OK);
    }

    @DeleteMapping("/developer/{devId}/games/{gameId}")
    public ResponseEntity<List<Game>> deleteDeveloperGame(@PathVariable long devId, @PathVariable long gameId) {
        return new ResponseEntity<>(developerService.deleteDeveloperGame(devId, gameId), HttpStatus.OK);
    }
}
