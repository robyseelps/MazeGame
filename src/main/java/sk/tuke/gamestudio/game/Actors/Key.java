package sk.tuke.gamestudio.game.Actors;


import sk.tuke.gamestudio.game.Gamefield.Tile;

public class Key extends AbstractActor {
    public Key(){
        setSprite(new Sprite("Key"));
        getSprite().setColor(SpriteColors.YELLOW);
    }
    public void pickUp(){
        for(Tile tile : getActorMap().getTileList()){
            if(!tile.getActors().isEmpty()){
                for(AbstractActor a : tile.getActors()){
                    if( a.getClass() == Exit.class && !((Exit) a).isWorking()){
                        tile.removeActor(a);
                        System.out.println("removed exit at " + a.getPosX() + " " + a.getPosY());
                        getTile().removeActor(this);
                        return;
                    }
                }
            }
        }
        getTile().removeActor(this);
    }
}
