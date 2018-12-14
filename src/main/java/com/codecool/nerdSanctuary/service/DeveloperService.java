package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.State;
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
        logger.info("CRUD operation: READ ALL Developer");
        return developerRepository.findAll();
    }

    public Developer getDeveloper(long id) {
        logger.info(String.format("CRUD operation: READ Developer ID=%s", id));
        if (developerRepository.exists(id)) {
            return developerRepository.findOne(id);
        }
        throw new ResourceNotFoundException(String.format("Developer ID = %s does not exist!", id));
    }

    public List<Game> getDeveloperGames(long id) {
        logger.info("CRUD operation: READ ALL Developer Game");
        if (developerRepository.exists(id)) {
            return getDeveloper(id).getGames();
        }
        throw new ResourceNotFoundException(String.format("Developer ID = %s does not exist!", id));
    }

    public Developer saveDeveloper(Developer developer) {
        logger.info(String.format("CRUD operation: CREATE Developer=%s", developer));
        if (developerRepository.existsByName(developer.getName())) {
            throw new ResourceNotFoundException(String.format("Developer %s does exist!", developer.getName()));
        }
        return developerRepository.save(developer);
    }

    public List<Game> addDeveloperGame(Game game, long id) {
        logger.info(String.format("CRUD operation: UPDATE Developer Game, GAME=%s", game));
        Developer developer = getDeveloper(id);
        if (gameRepository.existsByTitle(game.getTitle())) {
            developer.addGame(game);
            return developerRepository.save(developer).getGames();
        }
        throw new ResourceNotFoundException(String.format("Game=%s doesn't not exist in database!", game.getTitle()));
    }

    public Game getDeveloperGame(long devId, long gameId) {
        logger.info(String.format("CRUD operation: READ Developer Game ID=%s", gameId));
        return getDeveloper(devId).getGame(gameId);
    }

    public Developer updateDeveloper(long id, Developer updatedDev) {
        logger.info("CRUD operation: UPDATE Developer ID=%s", id);
        return developerRepository.save(getDeveloper(id).update(updatedDev));
    }


    public List<Developer> deleteDeveloper(long id) {
        logger.info(String.format("CRUD operation: DELETE Developer ID=%s", id));
        Developer developer = developerRepository.findOne(id);
        if(developer != null) {
            developer.getGames().forEach(game -> {
                game.setDeveloper(null);
                gameRepository.save(game);
            });
            developerRepository.delete(id);
            return developerRepository.findAll();
        }
        throw new ResourceNotFoundException(String.format("Developer=%s doesn't not exist in database!", id));
    }

    public List<Game> deleteDeveloperGame(long devId, long gameId) {
        logger.info(String.format("CRUD operation: DELETE Developer Game ID=%s", gameId));
        Developer developer = getDeveloper(devId);
        developer.removeGame(gameId);
        return developerRepository.save(developer).getGames();
    }
}
