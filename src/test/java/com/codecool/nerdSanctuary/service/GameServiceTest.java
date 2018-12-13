package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Genre;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//import org.junit.runner.RunWith;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Mock
    private GameRepository mockGameRepo;
    @Mock
    private PlatformRepository mockPlatformRepo;
    @Mock
    private DeveloperRepository mockDevRepo;
    @InjectMocks
    private GameService gameService;

    @Test
    void getGamesFromRepo() throws ParseException {
        List<Game> expected = createGameList();
        List<Game> actual;
        when(mockGameRepo.findAll()).thenReturn(expected);

        actual = gameService.getGames();

        assertEquals(expected, actual);
    }

    @Test
    void getOneGameById() throws ParseException {
        Game expected = createGameList().get(0);
        Game actual;
        when(mockGameRepo.findById(anyLong())).thenReturn(expected);
        when(mockGameRepo.exists((long) 0)).thenReturn(true);

        actual = gameService.getGame((long) 0);

        assertEquals(expected, actual);
    }

    @Test
    void getPlatformListOfGameWithGivenId() throws ParseException {
        Game sample = createGameList().get(0);
        List<Platform> expected = sample.getPlatforms();
        List<Platform> actual;
        when(mockGameRepo.findById(anyLong())).thenReturn(sample);
        when(mockGameRepo.exists((long) 0)).thenReturn(true);

        actual = gameService.getPlatforms((long) 0);

        assertEquals(expected, actual);
    }

    // Helpers
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

    private ArrayList<Developer> createDeveloperList() {
        ArrayList<Developer> list = new ArrayList<>();

        list.add(new Developer("Rockstar Games", "USA"));
        list.add(new Developer("CD Project Red", "Poland"));
        list.add(new Developer("From Software", "Japan"));
        list.add(new Developer("Team NINJA", "Japan"));
        list.add(new Developer("DICE", "Sweden"));

        return list;
    }

    private ArrayList<Game> createGameList() throws ParseException {
        ArrayList<Game> list = new ArrayList<>();
        ArrayList<Developer> d = createDeveloperList();
        ArrayList<Platform> pList = createPlatformList();
        Calendar c = Calendar.getInstance();
        Platform[] p;

        p = new Platform[]{pList.get(0), pList.get(2)};
        c.set(2010, Calendar.MAY,18);
        list.add(new Game("Red Dead Redemption", Genre.ACTION, c, new ArrayList<>(Arrays.asList(p)), d.get(0)));

        p = new Platform[]{pList.get(1), pList.get(3), pList.get(5)};
        c.set(2015, Calendar.MAY,19);
        list.add(new Game("The Witcher 3: Wild Hunt", Genre.RPG, c, new ArrayList<>(Arrays.asList(p)), d.get(1)));

        p = new Platform[]{pList.get(3), pList.get(5)};
        c.set(2018, Calendar.OCTOBER,26);
        list.add(new Game("Red Dead Redemption 2", Genre.ACTION, c, new ArrayList<>(Arrays.asList(p)), d.get(0)));

        p = new Platform[]{pList.get(5), pList.get(3)};
        c.set(2017, Calendar.FEBRUARY,7);
        list.add(new Game("NiOh", Genre.ACTION, c, new ArrayList<>(Arrays.asList(p)), d.get(3)));

        p = new Platform[]{pList.get(5), pList.get(3), pList.get(1)};
        c.set(2016, Calendar.MARCH,24);
        list.add(new Game("Dark Souls 3", Genre.ACTION, c, new ArrayList<>(Arrays.asList(p)), d.get(2)));

        p = new Platform[]{pList.get(1), pList.get(3), pList.get(5)};
        c.set(2018, Calendar.NOVEMBER,9);
        list.add(new Game("BattleField V", Genre.ACTION, c, new ArrayList<>(Arrays.asList(p)), d.get(4)));

        return list;
    }}