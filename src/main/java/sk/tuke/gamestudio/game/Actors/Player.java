package sk.tuke.gamestudio.game.Actors;

public class Player extends AbstractActor{
    private int posX;
    private int posY;
    private String playerName;
    private int playerScore;
    private int viewDistanceX = 15;
    private int viewDistanceY = 9;
    public Player(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        setSprite(new Sprite(".@."));
        getSprite().setColor(SpriteColors.BLUE);
        setPlayerName("Player");

    }
    public Player(){
        this.posX = 0;
        this.posY = 0;
        setSprite(new Sprite(".@."));
        getSprite().setColor(SpriteColors.BLUE);
        setPlayerName("Player");

    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
    public void addPlayerScore(int points){
        this.playerScore += points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
