package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private GameRepository gameRepository;

    private Logger logger = LogManager.getLogger();

    public List<Developer> getAllDeveloper() {
        return developerRepository.findAll();
    }

    public Developer getDeveloper(long id) {
        if (developerRepository.exists(id)) {
            return developerRepository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID = %s does not exist!"));
    }

    public List<Game> getDeveloperGames(long id) {
        return getDeveloper(id).getGames();
    }

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Game> addDeveloperGame(Game game, long id) {
        Developer developer = developerRepository.getOne(id);
        if (gameRepository.existsByTitle(game.getTitle())) {
            developer.addGame(game);
            return developerRepository.save(developer).getGames();
        }
        throw new IllegalArgumentException(String.format("Game=%s doesn not exist in database!", game.getTitle()));
    }

    public Game getDeveloperGame(long devId, long gameId) {
        return getDeveloper(devId).getGame(gameId);
    }

    public Developer updateDeveloper(long id, Developer updatedDev) {
        return developerRepository.save(developerRepository.findOne(id).update(updatedDev));
    }


    public List<Developer> deleteDeveloper(long id) {
        developerRepository.delete(id);
        return developerRepository.findAll();
    }

    public List<Game> deleteDeveloperGame(long devId, long gameId) {
        Developer developer = developerRepository.findOne(devId);
        developer.removeGame(gameId);
        return developerRepository.save(developer).getGames();
    }
}
