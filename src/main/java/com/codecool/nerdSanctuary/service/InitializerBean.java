package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Genre;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitializerBean {
    public InitializerBean(GameRepository gameRepo, DeveloperRepository devRepo, PlatformRepository platformRepo) throws ParseException {
        // create platforms
        ArrayList<Platform> platforms = createPlatformList();
        for (Platform p: platforms) {
            platformRepo.save(p);
        }
        // create developers
        ArrayList<Developer> developers = createDeveoperList();
        for (Developer d: developers) {
            devRepo.save(d);
        }
        // create Games
        ArrayList<Game> games = createGameList(platforms, developers);
        for (Game g: games) {
            gameRepo.save(g);
        }
    }

    private ArrayList<Platform> createPlatformList() {
        ArrayList<Platform> list = new ArrayList<>();

        list.add(new Platform("Xbox 360"));
        list.add(new Platform("Xbox One"));
        list.add(new Platform("PS 3"));
        list.add(new Platform("PS 4"));
        list.add(new Platform("Wii"));
        list.add(new Platform("PC"));
        list.add(new Platform("Android"));
        list.add(new Platform("IOS"));

        return list;
    }

    private ArrayList<Developer> createDeveoperList() {
        ArrayList<Developer> list = new ArrayList<>();

        list.add(new Developer("Rockstar Games", "USA"));
        list.add(new Developer("CD Project Red", "Poland"));
        list.add(new Developer("From Software", "Japan"));
        list.add(new Developer("Team NINJA", "Japan"));
        list.add(new Developer("DICE", "Sweden"));

        return list;
    }

    private ArrayList<Game> createGameList(ArrayList<Platform> pList, ArrayList<Developer> d) throws ParseException {
        ArrayList<Game> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        Platform[] p;
        p = new Platform[]{pList.get(0), pList.get(2)};
        list.add(new Game("Red Dead Redemption", Genre.ACTION, sdf.parse("2010-05-18"), new ArrayList<>(Arrays.asList(p)), d.get(0)));

        p = new Platform[]{pList.get(1), pList.get(3), pList.get(5)};
        list.add(new Game("The Witcher 3: Wild Hunt", Genre.RPG, sdf.parse("2015-05-19"), new ArrayList<>(Arrays.asList(p)), d.get(1)));

        p = new Platform[]{pList.get(3), pList.get(5)};
        list.add(new Game("Red Dead Redemption 2", Genre.ACTION, sdf.parse("2018-10-26"), new ArrayList<>(Arrays.asList(p)), d.get(0)));

        p = new Platform[]{pList.get(5), pList.get(3)};
        list.add(new Game("NiOh", Genre.ACTION, sdf.parse("2017-02-07"), new ArrayList<>(Arrays.asList(p)), d.get(3)));

        p = new Platform[]{pList.get(5), pList.get(3), pList.get(1)};
        list.add(new Game("Dark Souls 3", Genre.ACTION, sdf.parse("2016-03-24"), new ArrayList<>(Arrays.asList(p)), d.get(2)));

        p = new Platform[]{pList.get(1), pList.get(3), pList.get(5)};
        list.add(new Game("BattleField V", Genre.ACTION, sdf.parse("2018-11-09"), new ArrayList<>(Arrays.asList(p)), d.get(4)));

        return list;
    }
}
