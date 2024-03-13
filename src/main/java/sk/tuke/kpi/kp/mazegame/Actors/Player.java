package sk.tuke.kpi.kp.mazegame.Actors;

public class Player extends AbstractActor implements Alive{
    private int posX;
    private int posY;
    private int speed;
    private Health health;
    public Player(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        this.health = new Health(100);
        setSprite(new Sprite('P'));
        getSprite().setColor(SpriteColors.BLUE);
    }
    public Player(int posX,int posY,int speed){
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.health = new Health(100);
        setSprite(new Sprite('P'));
        getSprite().setColor(SpriteColors.BLUE);
    }
    public Player(){
        this.posX = 0;
        this.posY = 0;
        this.speed = 1;
        this.health = new Health(100);
        setSprite(new Sprite('P'));
        getSprite().setColor(SpriteColors.BLUE);
    }
    @Override
    public Health getHealth() {
        return health;
    }
    public void playerDied(){
        getSprite().setColor(SpriteColors.RED);
    }
}
