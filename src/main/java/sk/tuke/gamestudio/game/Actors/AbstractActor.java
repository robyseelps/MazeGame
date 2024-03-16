package sk.tuke.gamestudio.game.Actors;

import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.game.Gamefield.Tile;
public abstract class AbstractActor implements Actor {
    private int posX;

    private int posY;
    private Map actorMap;
    private Sprite sprite;
    @Override
    public int getPosX() {

        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void setPosX(int posX) {

        this.posX = posX;
    }

    public void setPosY(int posY) {

        this.posY = posY;
    }

    public Map getActorMap() {
        return actorMap;
    }

    public void setActorMap(Map actorMap) {

        this.actorMap = actorMap;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public boolean collisionCheck(AbstractActor a){
        return this.getPosX() == a.getPosX() && this.getPosY() == a.getPosY();
    }
    public Tile getTile(){
        return getActorMap().getMapArray()[getPosX()][getPosY()];
    }
}
