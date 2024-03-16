package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.game.Gamefield.MapBuilder;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        MapBuilder mapBuilder = new MapBuilder("/maps/maze.txt");
        Game game = new Game(mapBuilder.buildMap(),Gamestate.PLAYING);
        ConsoleUI consoleUI = new ConsoleUI(game);
        consoleUI.play();
    }
}
