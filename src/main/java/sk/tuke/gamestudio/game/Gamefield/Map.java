package sk.tuke.gamestudio.game.Gamefield;

import sk.tuke.gamestudio.game.Actors.*;

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
        this.mapArray = mapArray;
    }
    public Map(int rowCount, int columnCount, Tile[][] mapArray){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.player = null;
        tileList = createTileList(rowCount,columnCount,mapArray);
        this.mapArray = mapArray;
    }
    public List<Tile> createTileList(int rowCount,int columnCount,Tile[][] mapArray){
        List<Tile> tileList = new ArrayList<>();
        for(int i = 0; i < columnCount; i++){
            for(int j = 0; j < rowCount; j++){
                if(mapArray[i][j] == null)
                    mapArray[i][j] = new Tile(i,j);
                tileList.add(mapArray[i][j]);
                if(!mapArray[i][j].getActors().isEmpty()){
                    for (AbstractActor a : mapArray[i][j].getActors()){
                        a.setActorMap(this);
                    }
                }
            }
        }
        return tileList;
    }
    public void setPlayer(Player player) {
        this.player = player; player.setActorMap(this);
    }

    public Player getPlayer() {
        return player;
    }
    public List<AbstractActor> getPlayerCollision(){
       if( player.getTile().getActors().size() > 1 ){
           List<AbstractActor> collisionList = new ArrayList<AbstractActor>(player.getTile().getActors());
           collisionList.remove(player);
           return collisionList;
       }
       return null;
    }
    public boolean checkWon() {
        List<AbstractActor> collisionList = getPlayerCollision();
        if (collisionList != null) {
            return collisionList.stream()
                    .filter(actor -> actor instanceof Exit)
                    .map(actor -> (Exit) actor).anyMatch(Exit::isWorking);
        }
        return false;
    }
    public void updatePlayerCollision(){
        Tile currTIle = player.getTile();
        if(getPlayerCollision() == null)
            return;
        for(AbstractActor a : getPlayerCollision()) {
            if (a instanceof Key) {
                ((Key) a).pickUp();
                player.addPlayerScore(20);
            } else if (a instanceof Exit) {
                if (!((Exit) a).isWorking() && !((Exit) a).isActivated()) {
                    a.getSprite().setColor(SpriteColors.RED);
                    ((Exit) a).setActivated(true);
                    player.addPlayerScore(-60);
                    player.setPlayerHp(player.getPlayerHp() - 1);
                }
            }
        }
    }
    public void addActor(AbstractActor a){
        Tile tile = mapArray[a.getPosX()][a.getPosY()];
        tile.addActor(a);
        a.setActorMap(this);
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
