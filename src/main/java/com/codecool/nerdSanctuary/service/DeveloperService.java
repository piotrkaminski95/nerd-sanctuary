package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository repository;

    public List<Developer> getAllDeveloper() {
        return repository.findAll();
    }

    public Developer getDeveloper(long id) {
        if (repository.exists(id)) {
            return repository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID = %s does not exist!"));
    }

    public List<Game> getDeveloperGames(long id) {
        return getDeveloper(id).getGames();
    }

    public Developer saveDeveloper(Developer developer) {
        return repository.save(developer);
    }

    public List<Game> addDeveloperGame(Game game, long id) {
        Developer developer = repository.getOne(id);
        developer.addGame(game);
        return repository.save(developer).getGames();
    }
}
