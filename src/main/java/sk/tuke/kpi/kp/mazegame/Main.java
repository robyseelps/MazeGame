package sk.tuke.kpi.kp.mazegame;

import sk.tuke.kpi.kp.mazegame.Gamefield.MapBuilder;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        MapBuilder mapBuilder = new MapBuilder("/maps/sample.txt");
        Game game = new Game(mapBuilder.buildMap(),Gamestate.PLAYING);
        ConsoleUI consoleUI = new ConsoleUI(game);
        consoleUI.play();
    }
}
