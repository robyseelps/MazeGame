package sk.tuke.kpi.kp.mazegame.Actors;

import sk.tuke.kpi.kp.mazegame.Gamefield.Map;

public class AbstractActor implements Actor{
    private int posX;
    private int posY;
    private int speed;
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

    public void setSpeed(int speed) {

        this.speed = speed;
    }
    public int getSpeed() {

        return speed;
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
}
