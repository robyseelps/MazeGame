package sk.tuke.kpi.kp.mazegame.Gamefield;

import sk.tuke.kpi.kp.mazegame.Actors.AbstractActor;
import sk.tuke.kpi.kp.mazegame.Actors.SpriteColors;

import java.util.ArrayList;
import java.util.List;


public class Tile {
    private int posX;
    private int posY;
    private int width = 3;
    private TileType tileType;
    private  List<AbstractActor> Actors = new ArrayList<>();
    public Tile(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        setType(TileType.EMPTY);
    }
    public Tile(int posX, int posY, TileType tileType) {
        this.posX = posX;
        this.posY = posY;
        this.setType(tileType);
    }
    public Tile(int posX, int posY, TileType tileType, int width) {
        this.posX = posX;
        this.posY = posY;
        this.setType(tileType);
        setWidth(width);
    }

    public void setType(TileType type){
        this.tileType = type;
    }
    public TileType getType() {
        return tileType;
    }
    public void addActor(AbstractActor a){
        this.Actors.add(a);
        a.setPosition(posX,posY);
        if(this.getType() == TileType.EMPTY)
            this.setType(TileType.ACTOR);
    }
    public void removeActor(AbstractActor a){
        Actors.remove(a);
        if(Actors.isEmpty())
            this.setType(TileType.EMPTY);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public List<AbstractActor> getActors() {
        return Actors;
    }
    public String tileToString(){
        String s1;
        if(Actors.isEmpty()){
            if( getTileType() == TileType.EMPTY ){
                s1 = SpriteColors.BLACK.getString() +  " " + SpriteColors.RESET.getString();
            }
            else if (getTileType() == TileType.WALL){
                s1 = SpriteColors.GREY_BG.getString() +  " " + SpriteColors.RESET.getString();
            }
            else{
                return "Error";
            }
        }
        else {
            return Actors.getLast().getSprite().spriteToString();
        }
        StringBuilder stringBuilder = new StringBuilder(s1);
        for(int i = 1; i < width; i++){
            stringBuilder.append(s1);
        }
        return stringBuilder.toString();
    }
}
