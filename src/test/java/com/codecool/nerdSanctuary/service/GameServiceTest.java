package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.exceptions.BadRequestException;
import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.model.Genre;
import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.codecool.nerdSanctuary.repository.GameRepository;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    void getNotExistingGameThrowException() throws ParseException {
        Game sample = createGameList().get(0);
        String expectedMessage = String.format("Game ID=%s is not exist!", sample.getId());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists((long) 0)).thenReturn(false);

        Executable act = () -> { gameService.getGame(0); };

        assertThrows(expectedException, act, expectedMessage);
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

    @Test
    void getNotExistingGamePlatformsThrowException() throws ParseException {
        Game sample = createGameList().get(0);
        String expectedMessage = String.format("Game ID=%s is not exist!", sample.getId());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists((long) 0)).thenReturn(false);

        Executable act = () -> { gameService.getPlatforms(0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void getDeveloperOfGameWithGivenId() throws ParseException {
        Game sample = createGameList().get(0);
        Developer expected = sample.getDeveloper();
        Developer actual;
        when(mockGameRepo.findById(anyLong())).thenReturn(sample);
        when(mockGameRepo.exists((long) 0)).thenReturn(true);

        actual = gameService.getDeveloper((long) 0);

        assertEquals(expected, actual);
    }

    @Test
    void getNotExistingDeveloperThrowException() throws ParseException {
        Game sample = createGameList().get(0);
        String expectedMessage = String.format("Game ID=%s is not exist!", sample.getId());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists((long) 0)).thenReturn(false);

        Executable act = () -> { gameService.getDeveloper(0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void addGameToRepoAndReturnIt() throws ParseException {
        Game expected = createGameList().get(0);
        Game actual;
        when(mockGameRepo.findByTitle(expected.getTitle())).thenReturn(expected);

        actual = gameService.addGame(expected);

        verify(mockGameRepo).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void addExistingGameThrowException() throws ParseException {
        Game sample = createGameList().get(0);
        String expectedMessage = String.format("Game %s exist!", sample.getTitle());
        Executable act = () -> { gameService.addGame(sample); };
        Class<BadRequestException> expectedException = BadRequestException.class;
        when(mockGameRepo.existsByTitle(sample.getTitle())).thenReturn(true);

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void addPlatformToGameAndReturnIt() throws ParseException {
        Platform sample = new Platform("expected");
        Game expected = createGameList().get(0);
        Game actualGame;
        List<Platform> expectedPlatforms = new ArrayList<>(expected.getPlatforms());
        expectedPlatforms.add(sample);
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockPlatformRepo.existsByName(anyString())).thenReturn(true);
        when(mockGameRepo.findById(anyLong())).thenReturn(expected);

        actualGame = gameService.addPlatform(sample, 0);

        assertEquals(expected, actualGame);
        assertEquals(expectedPlatforms, actualGame.getPlatforms());
    }

    @Test
    void addExistingPlatformToNotExistingGameThrowException() throws ParseException {
        Platform sample = new Platform("expected");
        String expectedMessage = String.format("Game %s not exist!", 0);
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(false);

        Executable act = () -> { gameService.addPlatform(sample, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void addNotExistingPlatformToExistingGameThrowException() throws ParseException {
        Platform sample = new Platform("expected");
        String expectedMessage = String.format("Game %s not exist!", 0);
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockPlatformRepo.existsByName(anyString())).thenReturn(false);

        Executable act = () -> { gameService.addPlatform(sample, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void addPlatformToGameWhichIsAlreadyAddedThrowException() throws ParseException {
        Platform sample = new Platform("expected");
        Game game = createGameList().get(0);
        List<Platform> platforms = game.getPlatforms();
        platforms.add(sample);
        String expectedMessage = String.format("Platform %s exist", sample.getName());
        Class<BadRequestException> expectedException = BadRequestException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockPlatformRepo.existsByName(anyString())).thenReturn(true);
        when(mockGameRepo.findById(anyLong())).thenReturn(game);

        Executable act = () -> { gameService.addPlatform(sample, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editNotExistingGameThrowException() throws ParseException {
        List<Game> games = createGameList();
        Game sample = games.get(0);
        String expectedMessage = String.format("Game %s not exist!", sample.getTitle());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(false);

        Executable act = () -> { gameService.editGame(sample, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editGameWithProvidedId() throws ParseException {
        List<Game> games = createGameList();
        Game sample = games.get(0);
        Game expected = games.get(1);
        expected.setPlatforms(sample.getPlatforms());
        expected.setDeveloper(sample.getDeveloper());
        Game actual;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockGameRepo.findById(anyLong())).thenReturn(sample);

        actual = gameService.editGame(expected, 0);

        verify(mockGameRepo).save(any(Game.class));
        assertEquals(expected, actual);
    }

    @Test
    void editNotExistingGamePlatformsThrowException() throws ParseException {
        String expectedMessage = String.format("Game %s not exist!", 0);
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(false);

        Executable act = () -> { gameService.editGamePlatforms(new ArrayList<>(), 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editGameWithNotExistingPlatformThrowException() throws ParseException {
        Platform platform = new Platform("expected");
        ArrayList<Platform> platforms = new ArrayList<>();
        platforms.add(platform);
        String expectedMessage = String.format("Platform %s is not exist!", platform.getName());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockPlatformRepo.existsByName(anyString())).thenReturn(false);

        Executable act = () -> { gameService.editGamePlatforms(platforms, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editGamePlatformsWithProvidedId() throws ParseException {
        List<Game> games = createGameList();
        Game sample = games.get(0);
        List<Platform> expected = sample.getPlatforms();
        expected.add(new Platform("expected"));
        Game actual;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockPlatformRepo.existsByName(anyString())).thenReturn(true);
        when(mockGameRepo.findById(anyLong())).thenReturn(sample);

        actual = gameService.editGamePlatforms(expected, 0);

        verify(mockGameRepo).save(any(Game.class));
        assertEquals(expected, actual.getPlatforms());
    }

    @Test
    void editNotExistingGameDeveloperThrowException() throws ParseException {
        String expectedMessage = String.format("Game %s not exist!", 0);
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(false);

        Executable act = () -> { gameService.editDeveloper(mock(Developer.class), 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editGameWithNotExistingDeveloperThrowException() throws ParseException {
        Developer developer = new Developer();
        developer.setName("expected");
        String expectedMessage = String.format("Developer %s is not exist!", developer.getName());
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockDevRepo.existsByName(anyString())).thenReturn(false);

        Executable act = () -> { gameService.editDeveloper(developer, 0); };

        assertThrows(expectedException, act, expectedMessage);
    }

    @Test
    void editGameDeveloperWithProvidedId() throws ParseException {
        List<Game> games = createGameList();
        Game sample = games.get(0);
        Developer expected = new Developer();
        expected.setName("expected");
        Game actual;
        when(mockGameRepo.exists(anyLong())).thenReturn(true);
        when(mockDevRepo.existsByName(anyString())).thenReturn(true);
        when(mockGameRepo.findById(anyLong())).thenReturn(sample);
        when(mockDevRepo.findByName(anyString())).thenReturn(expected);


        actual = gameService.editDeveloper(expected, 0);

        verify(mockGameRepo).save(any(Game.class));
        assertEquals(expected, actual.getDeveloper());
    }

    @Test
    void deleteGameFromRepoAndReturnIt() throws ParseException {
        Game expected = createGameList().get(0);
        Game actual;
        when(mockGameRepo.findById(anyLong())).thenReturn(expected);
        when(mockGameRepo.exists(anyLong())).thenReturn(true);

        actual = gameService.deleteGame(0);

        verify(mockGameRepo).delete(expected);
        assertEquals(expected, actual);
    }

    @Test
    void deleteNotExistingGameThrowException() throws ParseException {
        Game sample = createGameList().get(0);
        String expectedMessage = String.format("Game ID=%s is no exist!", 0);
        Executable act = () -> { gameService.deleteGame(0); };
        Class<ResourceNotFoundException> expectedException = ResourceNotFoundException.class;
        when(mockGameRepo.exists(anyLong())).thenReturn(false);

        assertThrows(expectedException, act, expectedMessage);
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