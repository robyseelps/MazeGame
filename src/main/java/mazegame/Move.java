package main.java.mazegame;

public class Move {
    public void move(int x, int y, Actor actor,Map map){
        if(isMovePossible(x,y,map)){
            for(Tile tile : map.getTileList()){
                if (tile.getActors().contains(actor))
                    tile.removeActor(actor);
                map.getMapArray()[x][y].addActor(actor);
            }
        }
    }
    public boolean isMovePossible(int x,int y, Map map){
        if(map!= null && map.getMapArray() != null){
            return map.getMapArray()[x][y].getTileType() != TileType.WALL;
        }
        return false;
    }
}
