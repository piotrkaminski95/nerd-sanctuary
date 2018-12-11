package com.codecool.nerdSanctuary.model;

public enum Genre {
    ACTION,
    RPG,
    MMO,
    SPORT,
    LOGIC,
    SYMULATION,
    STRATEGY,
    RACING,
    ARCADE,
    FIGHT;

    public static Genre getGenre(String s) throws IllegalArgumentException, NullPointerException {
        for (Genre g: Genre.values()) {
            if (g.toString().equals(s.toUpperCase())) {
                return g;
            }
        }
        throw new IllegalArgumentException();
    }
}
