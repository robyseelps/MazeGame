package sk.tuke.kpi.kp.mazegame.Actors;

public enum SpriteColors {

    GREY_BG("\u001B[40m\u001B[90m"),
    BLACK("\u001B[30m\u001B[30m"),
    RED("\u001B[30m\u001B[31m"),
    GREEN("\u001B[30m\u001B[32m"),
    YELLOW("\u001B[30m\u001B[33m"),
    BLUE("\u001B[30m\u001B[34m"),
    PURPLE("\u001B[30m\u001B[35m"),
    CYAN("\u001B[30m\u001B[36m"),
    WHITE("\u001B[30m\u001B[37m"),
    RESET("\u001B[0m");


    private final String string;

    SpriteColors(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
