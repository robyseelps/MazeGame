package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Gamefield.MapBuilder;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main (String[] args) throws IOException, ClassNotFoundException {

        MapBuilder mapBuilder = new MapBuilder("/maps/maze.txt");
        Game game = new Game(mapBuilder.buildMap(),Gamestate.PLAYING);
        ConsoleUI consoleUI = new ConsoleUI(game);
        consoleUI.play();
    }
}
