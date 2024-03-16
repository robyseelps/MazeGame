package sk.tuke.kpi.kp.mazegame;

import sk.tuke.kpi.kp.mazegame.Gamefield.Map;

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
    public void updateGame() {
        if (getGamestate() == Gamestate.PLAYING) {
            if (map.checkWon())
                gamestate = Gamestate.WON;
            map.updatePlayerCollision();
        }
    }
}
