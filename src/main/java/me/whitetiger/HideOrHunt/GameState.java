package me.whitetiger.HideOrHunt;

public enum GameState {
    WAITING("WAITING"),
    ACTIVE("ACTIVE"),
    PAUSED("PAUSED");

    final String name;

    GameState(String name) {
        this.name = name;
    }
}
