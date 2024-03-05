package mazegame;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        MapBuilder mapBuilder = new MapBuilder("maps/sample.txt");
        Game game = new Game(mapBuilder.buildMap(),Gamestate.PLAYING);
        ConsoleUI consoleUI = new ConsoleUI(game);
    }
}
