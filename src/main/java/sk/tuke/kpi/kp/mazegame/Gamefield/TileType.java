package sk.tuke.kpi.kp.mazegame.Gamefield;

import sk.tuke.kpi.kp.mazegame.Actors.SpriteColors;

public enum TileType {
    WALL,EMPTY,ACTOR;
    public static TileType fromSymbol(char symbol){
        if(symbol == 'X')
            return TileType.WALL;
        else if (symbol == '.')
            return TileType.EMPTY;
        return TileType.ACTOR;
    }
}


