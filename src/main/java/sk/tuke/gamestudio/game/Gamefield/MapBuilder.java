package sk.tuke.gamestudio.game.Gamefield;

import sk.tuke.gamestudio.game.Actors.Exit;
import sk.tuke.gamestudio.game.Actors.Key;
import sk.tuke.gamestudio.game.Actors.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapBuilder {
    private String filePath;

    public MapBuilder(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map buildMap() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IOException("Error loading resources: " + filePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int rowCount = 0;
        int columnCount = 0;
        while ((line = reader.readLine()) != null) {
            columnCount = Math.max(columnCount, line.length());
            rowCount++;
        }
        reader.close();
        Tile[][] mapArray = new Tile[columnCount][rowCount];
        Player player = null;
        inputStream = getClass().getResourceAsStream(filePath);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        int y = 0;
        while ((line = reader.readLine()) != null) {
            for (int x = 0; x < line.length(); x++) {
                char symbol = line.charAt(x);
                TileType type = TileType.fromSymbol(symbol);
                Tile tile = new Tile(x, y, type);

                if (type == TileType.ACTOR) {
                    if(symbol == 'O'){
                        Exit exit = new Exit(true);
                        tile.addActor(exit);
              //          System.out.println("Exit created at " + x + " " + y);
                    }
                    else if(symbol == '0') {
                        Exit exit = new Exit(false);
                        tile.addActor(exit);
               //         System.out.println("Exit created at " + x + " " + y);
                    }
                    else if(symbol == 'P') {
                        player = new Player(x, y);
                        tile.addActor(player);
              //          System.out.println("Player created at " + x + " " + y);
                    }
                    else if(symbol == 'K') {
                        Key key = new Key();
                        tile.addActor(key);
              //          System.out.println("Key created at " + x + " " + y);
                    }
                    }
                mapArray[x][y] = tile;
            }
            y++;
        }
        reader.close();
        return new Map(rowCount, columnCount, mapArray, player);
    }
}
