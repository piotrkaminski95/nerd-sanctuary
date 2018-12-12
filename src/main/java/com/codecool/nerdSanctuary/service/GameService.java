package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private BodyParserFactory requestBodyParserFactory;

    public GameService(BodyParserFactory requestBodyParserFactory) {
        this.requestBodyParserFactory = requestBodyParserFactory;
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<?> addGame(HashMap<String, Object> gameMap) {
        if (gameMap == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        try {
            RequestBodyParser<Game> requestBodyParser = requestBodyParserFactory.getInstance("gameRequestBodyParser");

            Game game = requestBodyParser.parseBody(gameMap);
            if (gameRepo.existsByTitle(game.getTitle())) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            gameRepo.save(game);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NullPointerException | ClassNotFoundException | IllegalArgumentException | ClassCastException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
