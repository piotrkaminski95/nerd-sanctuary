package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.exceptions.BadRequestException;
import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private PlatformRepository platformRepo;
    @Autowired
    private DeveloperRepository devRepo;

    private Logger logger = LogManager.getLogger();

    public GameService() {}

    public List<Game> getGames() {
        logger.info("CRUD operation: READ ALL GAMES");
        return gameRepo.findAll();
    }

    public Game getGame(long id) {
        logger.info(String.format("CRUD operation: READ GAME ID=%s", id));
        if (!gameRepo.exists(id)) {
            throw new ResourceNotFoundException(String.format("Game ID: %s is not exist!", id));
        }
        return gameRepo.findById(id);
    }

    public List<Platform> getPlatforms(long id) {
        logger.info(String.format("CRUD operation: READ GAME ID=%s PLATFORMS", id));

        if (!gameRepo.exists(id)) {
            throw new ResourceNotFoundException(String.format("Game ID=%s is not exist!", id));
        }
        Game game = gameRepo.findById(id);
        return game.getPlatforms();
    }

    public Developer getDeveloper(long id) {
        logger.info(String.format("CRUD operation: GET GAME ID=%s DEVELOPER", id));
        if (!gameRepo.exists(id)) {
            throw new ResourceNotFoundException(String.format("ERROR: ID=%s IS NOT EXIST!", id));
        }
        Game game = gameRepo.findById(id);
        return game.getDeveloper();
    }

    public Game addGame(Game newGame) {
        logger.info("CRUD operation: ADD GAME");
        if (gameRepo.existsByTitle(newGame.getTitle())) {
            throw new BadRequestException(String.format("Game %s exist!", newGame.getTitle()));
        }
        gameRepo.save(newGame);
        return gameRepo.findByTitle(newGame.getTitle());
    }

    public Game addPlatform(Platform platform, long id) {
        logger.info(String.format("CRUD operation: ADD PLATFORM TO GAME ID=%s", id));
        if (!gameRepo.exists(id) || !platformRepo.existsByName(platform.getName())) {
            throw new ResourceNotFoundException(String.format("Game ID=%s is not exist!", id));
        }
        Game game = gameRepo.findById(id);
        List<Platform> list = game.getPlatforms();
        if (list.contains(platform)) {
            throw new BadRequestException(String.format("Platform %s exist", platform.getName()));
        }
        list.add(platform);
        game.setPlatforms(list);
        gameRepo.save(game);
        return game;
    }

    public Game editGame(Game game, long id) {
        logger.info(String.format("CRUD operation: UPDATE GAME ID=%s", id));
        if (!gameRepo.exists(id)) {
            throw  new ResourceNotFoundException(String.format("Game ID=%s is not exist!", id));
        }
        Game oldGame = gameRepo.findById(id);
        game.setId(id);
        game.setPlatforms(oldGame.getPlatforms());
        game.setDeveloper(oldGame.getDeveloper());
        gameRepo.save(game);
        return game;
    }

    public Game editGamePlatforms(List<Platform> platforms, long id) {
        logger.info(String.format("CRUD operation: UPDATE GAME ID=%s PLATFORM LIST", id));
        if (!gameRepo.exists(id)) {
            throw new ResourceNotFoundException(String.format("Game ID=%s is not exist!", id));
        }
        for (Platform p : platforms) {
            if (!platformRepo.existsByName(p.getName())) {
                throw new ResourceNotFoundException(String.format("Platform %s is not exist!", p.getName()));
            }
        }

        Game game = gameRepo.findById(id);
        game.setPlatforms(platforms);
        gameRepo.save(game);
        return game;
    }

    public Game editDeveloper(Developer developer, long id) {
        logger.info(String.format("CRUD operation: UPDATE GAME ID=%s DEVELOPER", id));
        if (!gameRepo.exists(id)) {
            throw new ResourceNotFoundException(String.format("Game ID=%s is not exist!", id));
        }

        if (!devRepo.existsByName(developer.getName())) {
            logger.info(String.format("Developer %s is not exist!", developer.getName()));
        }

        Game game = gameRepo.findById(id);
        game.setDeveloper(devRepo.findByName(developer.getName()));
        gameRepo.save(game);
        return game;
    }

    public Game deleteGame(long id) {
        logger.info(String.format("CRUD operation: DELETE GAME ID=%s", id));
        if (!gameRepo.exists(id)) {
            logger.info(String.format("Game ID=%s is no exist!", id));
        }

        Game game = gameRepo.findById(id);

        gameRepo.delete(game);
        return game;
    }
}
