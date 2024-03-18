package sk.tuke.gamestudio.game.Actors;

public class Exit extends AbstractActor{
    private boolean isWorking;
    private boolean wasActivated;
    public Exit(){
        setSprite(new Sprite("[O]"));
        getSprite().setColor(SpriteColors.PURPLE);
        setWorking(false);
        setActivated(false);
    }
    public Exit(boolean isWorking){
        setSprite(new Sprite("[O]"));
        getSprite().setColor(SpriteColors.PURPLE);
        setWorking(isWorking);
        setActivated(false);
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public boolean isActivated() {
        return wasActivated;
    }

    public void setActivated(boolean wasActivated) {
        this.wasActivated = wasActivated;
    }
}
