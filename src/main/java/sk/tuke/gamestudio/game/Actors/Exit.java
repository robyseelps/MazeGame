package sk.tuke.gamestudio.game.Actors;

public class Exit extends AbstractActor{
    private boolean isWorking;
    public Exit(){
        setSprite(new Sprite("[O]"));
        getSprite().setColor(SpriteColors.PURPLE);
        setWorking(false);
    }
    public Exit(boolean isWorking){
        setSprite(new Sprite("[O]"));
        getSprite().setColor(SpriteColors.PURPLE);
        setWorking(isWorking);
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
