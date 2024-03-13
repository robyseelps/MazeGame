package sk.tuke.kpi.kp.mazegame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapBuilder {
    private String filePath;

    public MapBuilder(String filePath) {
        this.filePath = filePath;
    }

    public Map buildMap() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IOException("Failed to load resource: " + filePath);
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
                if (type == TileType.ACTOR) {
                    player = new Player(x, y);
                }
                mapArray[x][y] = new Tile(x, y, type);
            }
            y++;
        }
        reader.close();

        return new Map(rowCount, columnCount, mapArray, player);
    }
}
