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
        logger.info("CRUD operation: READ ALL DEVELOPERS");
        return developerRepository.findAll();
    }

    public Developer getDeveloper(long id) {
        logger.info(String.format("CRUD operation: READ DEVELOPER ID=%s", id));
        if (developerRepository.exists(id)) {
            return developerRepository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID = %s does not exist!"));
    }

    public List<Game> getDeveloperGames(long id) {
        logger.info("CRUD operation: READ ALL DEVELOPER GAMES");
        return getDeveloper(id).getGames();
    }

    public Developer saveDeveloper(Developer developer) {
        logger.info(String.format("CRUD operation: CREATE Developer=%s", developer));
        return developerRepository.save(developer);
    }

    public List<Game> addDeveloperGame(Game game, long id) {
        logger.info(String.format("CRUD operation: UPDATE Developer games, GAME=%s", game));
        Developer developer = getDeveloper(id);
        if (gameRepository.existsByTitle(game.getTitle())) {
            developer.addGame(game);
            return developerRepository.save(developer).getGames();
        }
        throw new IllegalArgumentException(String.format("Game=%s doesn't not exist in database!", game.getTitle()));
    }

    public Game getDeveloperGame(long devId, long gameId) {
        logger.info(String.format("CRUD operation: READ DEVELOPER GAME ID=%s", gameId));
        return getDeveloper(devId).getGame(gameId);
    }

    public Developer updateDeveloper(long id, Developer updatedDev) {
        logger.info("CRUD operation: UPDATE DEVELOPER ID=%s", id);
        return developerRepository.save(developerRepository.findOne(id).update(updatedDev));
    }


    public List<Developer> deleteDeveloper(long id) {
        logger.info(String.format("CRUD operation: DELETE DEVELOPER ID=%s", id));
        developerRepository.delete(id);
        return developerRepository.findAll();
    }

    public List<Game> deleteDeveloperGame(long devId, long gameId) {
        logger.info(String.format("CRUD operation: DELETE DEVELOPER GAME ID=%s", gameId));
        Developer developer = getDeveloper(devId);
        developer.removeGame(gameId);
        return developerRepository.save(developer).getGames();
    }
}
