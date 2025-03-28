package sk.tuke.gamestudio.game.Actors;

public class Sprite {
    // because of command console limitations sprites are represented by char symbols

    private String symbol;
    private SpriteColors color;
    public Sprite(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setColor(SpriteColors color) {
        this.color = color;
    }
   // public String spriteToString(){
   //     return color.getString() + symbol + SpriteColors.RESET.getString();
   // }
    public String spriteToString(){
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
