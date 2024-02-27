package main.java.mazegame;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private int rowCount;
    private int columnCount;
    private Tile[][] mapArray;
    private List<Tile> tileList;
    private Player player;
    public Map(int rowCount, int columnCount){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mapArray = new Tile[columnCount][rowCount];
        tileList = createTileList(rowCount,columnCount,mapArray);
        this.player = null;
    }
    public Map(int rowCount, int columnCount, Player player){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mapArray = new Tile[columnCount][rowCount];
        tileList = createTileList(rowCount,columnCount,mapArray);
        this.player = player;
    }
    public Map(int rowCount, int columnCount, Tile[][] mapArray,Player player){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.player = player;
        tileList = createTileList(rowCount,columnCount,mapArray);
    }
    public Map(int rowCount, int columnCount, Tile[][] mapArray){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.player = null;
        tileList = createTileList(rowCount,columnCount,mapArray);
    }
    public List<Tile> createTileList(int rowCount,int columnCount,Tile[][] mapArray){
        List<Tile> tileList = new ArrayList<>();
        for(int i = 0; i < columnCount; i++){
            for(int j = 0; j < rowCount; j++){
                if(mapArray[i][j] == null)
                    mapArray[i][j] = new Tile(i,j);
                tileList.add(mapArray[i][j]);
            }
        }
        return tileList;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public boolean isWon(){
        Tile exit = null;
        for (Tile tile : tileList) {
            if (tile.getTileType() == TileType.EXIT)
                exit = tile;
        }
        if(exit != null){
            return player.getPosX() == exit.getPosX() && player.getPosY() == exit.getPosY();
        }
        return false;
    }
    public void addActor(Actor a){
        mapArray[a.getPosX()][a.getPosY()].addActor(a);
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public Tile[][] getMapArray() {
        return mapArray;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
