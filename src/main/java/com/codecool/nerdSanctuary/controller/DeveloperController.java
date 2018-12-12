package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DeveloperController {

//    TO-DO: This class!
//    @Autowired
//    DeveloperService service;

    @Autowired
    private DeveloperRepository repository;

    @Autowired
    private GameRepository gameRepository;

    public void setRepository(DeveloperRepository repository) {
        this.repository = repository;
    }


    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    /*public void setService(DeveloperService service) {
        this.service = service;
    }
     */
    // i don't know but i have idea to send here some graphical content it will be nicer
//    @GetMapping("/developer/all")
    @GetMapping("/developer")
    public List<Developer> getAllDevelopers() {
        return repository.findAll();
    }


    @GetMapping("/developer/{id}")
    public Developer getDeveloper(@PathVariable long id) {
        if (repository.exists(id)) {
            return repository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID = %s does not exist!"));
//        return service.getDeveloper(id);
    }


    @GetMapping("/developer/{id}/games")
    public List<Game> getDeveloperGames(@PathVariable long id) {
       return getDeveloper(id).getGames();
//        return service.getDeveloperGames(id);
    }


    @PostMapping("/developer")
    public Developer postDeveloper(@Valid @RequestBody Developer developer) {
        return repository.save(developer);
    }


    @PostMapping("/developer/{id}/games")
    public List<Game> postDeveloperGame(@PathVariable long id, @Valid @RequestBody Game game) {
        Developer developer = repository.getOne(id);

        developer.addGame(game);
        return repository.save(developer).getGames();
//        return service.addDeveloperGame(game);
    }
}
