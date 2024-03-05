package mazegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapBuilder {
    private String filePath;

    public MapBuilder(String filePath) {
        this.filePath = filePath;
    }

    public Map buildMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
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
        reader = new BufferedReader(new FileReader(filePath));
        int y = 0;
        while ((line = reader.readLine()) != null) {
            for (int x = 0; x < line.length(); x++) {
                char symbol = line.charAt(x);
                TileType type = TileType.fromSymbol(symbol);
                if (type == TileType.ACTOR) {
                    player = new Player(x, y);
                    type = TileType.EMPTY;
                }
                mapArray[x][y] = new Tile(x, y, type);
            }
            y++;
        }
        reader.close();

        return new Map(rowCount, columnCount, mapArray, player);
    }
}
