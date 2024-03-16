package sk.tuke.kpi.kp.mazegame.Actors;

public class Player extends AbstractActor{
    private int posX;
    private int posY;

    private int viewDistanceX = 20;
    private int viewDistanceY = 5;
    public Player(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        setSprite(new Sprite(".@."));
        getSprite().setColor(SpriteColors.BLUE);

    }
    public Player(){
        this.posX = 0;
        this.posY = 0;
        setSprite(new Sprite(".@."));
        getSprite().setColor(SpriteColors.BLUE);

    }

    public int getViewDistanceX() {
        return viewDistanceX;
    }

    public void setViewDistanceX(int viewDistanceX) {
        this.viewDistanceX = viewDistanceX;
    }

    public int getViewDistanceY() {
        return viewDistanceY;
    }

    public void setViewDistanceY(int viewDistanceY) {
        this.viewDistanceY = viewDistanceY;
    }


}
