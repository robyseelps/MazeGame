package sk.tuke.kpi.kp.mazegame.Actors;

public class Sprite {
    // because of command console limitations sprites are represented by char symbols

    private char symbol;
    private SpriteColors color;
    public Sprite(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setColor(SpriteColors color) {
        this.color = color;
    }
    public String spriteToString(){
        return color.getString() + symbol + SpriteColors.RESET.getString();
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
