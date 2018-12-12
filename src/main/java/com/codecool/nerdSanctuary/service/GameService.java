package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
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

//    toRemove
//    @Autowired
//    private BodyParserFactory requestBodyParserFactory;

//    public GameService(BodyParserFactory requestBodyParserFactory) {
//        this.requestBodyParserFactory = requestBodyParserFactory;
//    }

//    @SuppressWarnings("unchecked")
//    public ResponseEntity<?> addGame(HashMap<String, Object> gameMap) {
//        if (gameMap == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        try {
//            RequestBodyParser<Game> requestBodyParser = requestBodyParserFactory.getInstance("gameRequestBodyParser");
//
//            Game game = requestBodyParser.parseBody(gameMap);
//            if (gameRepo.existsByTitle(game.getTitle())) {
//                return new ResponseEntity(HttpStatus.NOT_FOUND);
//            }
//            gameRepo.save(game);
//            return new ResponseEntity(HttpStatus.OK);
//        } catch (NullPointerException | ClassNotFoundException | IllegalArgumentException | ClassCastException e) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }

    public GameService() {}

    public List<Game> getGames() {
        return gameRepo.findAll();
    }

    public Game getGame(long id) {
        return gameRepo.findById(id);
    }

    public List<Platform> getPlatforms(long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }
        Game game = gameRepo.findById(id);
        return game.getPlatforms();
    }

    public Developer getDeveloper(long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }
        Game game = gameRepo.findById(id);
        return game.getDeveloper();
    }

    public Game addGame(Game newGame) {
        if (gameRepo.existsByTitle(newGame.getTitle())) {
            return null;
        }
        gameRepo.save(newGame);
        return gameRepo.findByTitle(newGame.getTitle());
    }

    public Game addPlatform(Platform platform, long id) {
        if (!gameRepo.exists(id) || !platformRepo.existsByName(platform.getName())) {
            return null;
        }
        Game game = gameRepo.findById(id);
        List<Platform> list = game.getPlatforms();
        if (list.contains(platform)) {
            return null;
        }
        list.add(platform);
        game.setPlatforms(list);
        gameRepo.save(game);
        return game;
    }

    public Game editGame(Game game, long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }
        Game oldGame = gameRepo.findById(id);
        game.setId(id);
        game.setPlatforms(oldGame.getPlatforms());
        game.setDeveloper(oldGame.getDeveloper());
        gameRepo.save(game);
        return game;
    }

    public Game editGamePlatforms(List<Platform> platforms, long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }

        for (Platform p : platforms) {
            if (!platformRepo.existsByName(p.getName())) {
                return null;
            }
        }

        Game game = gameRepo.findById(id);
        game.setPlatforms(platforms);
        gameRepo.save(game);
        return game;
    }

    public Game editDeveloper(Developer developer, long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }

        if (!devRepo.existsByName(developer.getName())) {
            return null;
        }

        Game game = gameRepo.findById(id);
        game.setDeveloper(devRepo.findByName(developer.getName()));
        gameRepo.save(game);
        return game;
    }

    public Game deleteGame(long id) {
        if (!gameRepo.exists(id)) {
            return null;
        }

        Game game = gameRepo.findById(id);

        gameRepo.delete(game);
        return game;
    }
}
