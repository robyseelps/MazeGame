package mazegame;

import java.util.ArrayList;
import java.util.List;


public class Tile {
    private int posX;
    private int posY;
    private TileType tileType;
    private List<Actor> Actors = new ArrayList<>();
    public Tile(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public Tile(int posX, int posY, TileType tileType) {
        this.posX = posX;
        this.posY = posY;
        this.setType(tileType);
    }

    public void setType(TileType type){
        this.tileType = type;
    }
    public TileType getType() {
        return tileType;
    }
    public void addActor(Actor a){
        Actors.add(a);
        if(this.getType() == TileType.EMPTY)
            this.setType(TileType.ACTOR);
    }
    public void removeActor(Actor a){
        Actors.remove(a);
        if(Actors.isEmpty())
            this.setType(TileType.EMPTY);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public TileType getTileType() {
        return tileType;
    }

    public List<Actor> getActors() {
        return Actors;
    }
}
