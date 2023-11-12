package main;

public enum GameStates {

    MENU, PLAYING, EDIT, SETTINGS;

    public static GameStates gameState = MENU;

    public static void setGameStates( GameStates state ) {
        gameState = state;
    }
}
