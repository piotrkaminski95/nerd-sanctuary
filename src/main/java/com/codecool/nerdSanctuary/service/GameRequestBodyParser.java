package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Genre;
import com.codecool.nerdSanctuary.model.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class GameRequestBodyParser implements RequestBodyParser<Game> {
    @Autowired
    private BodyParserFactory bodyParserFactory;

    @SuppressWarnings("unchecked")
    @Override
    public Game parseBody(Map<String, Object> data) throws IllegalArgumentException, ClassCastException {
        try {
            RequestBodyParser<Developer> devBodyParser = bodyParserFactory.getInstance("developerRequestBodyParser");
            Game game = new Game();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

            game.setTitle((String) data.get("title"));
            game.setGenre(Genre.getGenre((String) data.get("genre")));
            c.setTime(sdf.parse((String) data.get("releaseDate")));
            game.setReleaseDate(c);
            game.setPlatforms(parsePlatformsList(data.get("platforms")));
            game.setDeveloper(devBodyParser.parseBody((Map<String, Object>) data.get("developer")));
            return game;
        } catch (ClassNotFoundException | ParseException e) {
            throw new ClassCastException();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Platform> parsePlatformsList(Object platforms) throws ClassNotFoundException {
        RequestBodyParser<Platform> bodyParser = bodyParserFactory.getInstance("platformRequestBodyParser");
        List<Platform> list = new ArrayList<>();
        for (Object o : (List<Object>) platforms) {
            list.add(bodyParser.parseBody((Map<String, Object>) o));
        }
        return list;
    }
}
