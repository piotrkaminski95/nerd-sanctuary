package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Developer> getAllDevelopers() {
        return developerService.getAllDeveloper();
    }

    @GetMapping("/developer/{id}")
    public Developer getDeveloper(@PathVariable long id) {
        return developerService.getDeveloper(id);
    }

    @GetMapping("/developer/{id}/games")
    public List<Game> getDeveloperGames(@PathVariable long id) {
        return developerService.getDeveloperGames(id);
    }

    @GetMapping("/developer/{devId}/games/{gameId}")
    public Game getDeveloperGame(@PathVariable long devId, @PathVariable long gameId) {
        return developerService.getDeveloperGame(devId, gameId);
    }

    @PostMapping("/developer")
    public Developer postDeveloper(@Valid @RequestBody Developer developer) {
        return developerService.saveDeveloper(developer);
    }

    @PostMapping("/developer/{id}/games")
    public List<Game> postDeveloperGame(@PathVariable long id, @Valid @RequestBody Game game) {
        return developerService.addDeveloperGame(game, id);
    }

    @PutMapping("/developer/{id}")
    public Developer updateDeveloper(@PathVariable long id, @Valid @RequestBody Developer updatedDev) {
        return developerService.updateDeveloper(id, updatedDev);
    }

    @DeleteMapping("/developer/{id}")
    public List<Developer> deleteDeveloper(@PathVariable long id) {
        return developerService.deleteDeveloper(id);
    }

    @DeleteMapping("/developer/{devId}/games/{gameId}")
    public List<Game> deleteDeveloperGame(@PathVariable long devId, @PathVariable long gameId) {
        return developerService.deleteDeveloperGame(devId, gameId);
    }
}
