package mazegame;

public enum TileType {
    WALL,EMPTY,ACTOR,EXIT;
    public static TileType fromSymbol(char symbol){
        if(symbol == 'X')
            return TileType.WALL;
        else if (symbol == 'O')
            return TileType.EXIT;
        else if (symbol == 'P')
            return TileType.ACTOR;
        return TileType.EMPTY;
    }
}


