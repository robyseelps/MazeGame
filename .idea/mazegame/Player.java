public class Player {
    private int posX;
    private int posY;
    private int speed;
    public Player(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }
    public Player(int posX,int posY,int speed){
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }
    public Player(){
        this.posX = 0;
        this.posY = 0;
        this.speed = 1;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
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
}
