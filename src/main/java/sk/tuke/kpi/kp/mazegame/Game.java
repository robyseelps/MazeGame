package sk.tuke.kpi.kp.mazegame;

public class Game {
    private Map map;
    private Gamestate gamestate;
    public Game(Map map, Gamestate gamestate){
        this.map = map;
        this.gamestate = gamestate;
    }

    public Map getMap() {
        return map;
    }

    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }
}
