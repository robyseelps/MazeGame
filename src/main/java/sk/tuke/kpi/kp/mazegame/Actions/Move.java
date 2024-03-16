package sk.tuke.kpi.kp.mazegame.Actions;

import sk.tuke.kpi.kp.mazegame.Actors.AbstractActor;
import sk.tuke.kpi.kp.mazegame.Gamefield.Map;
import sk.tuke.kpi.kp.mazegame.Gamefield.Tile;
import sk.tuke.kpi.kp.mazegame.Gamefield.TileType;

public class Move {
    public void move(int x, int y, AbstractActor actor, Map map) {
        if (isMovePossible(x, y, map)) {
            if (map != null && map.getMapArray() != null && map.getMapArray()[x][y] != null) {
                Tile currentTile = map.getMapArray()[actor.getPosX()][actor.getPosY()];
                Tile newTile = map.getMapArray()[x][y];
                if (currentTile.getActors().isEmpty()) {
                    System.out.println("Error! No actor found on the tile " + currentTile.getPosX() + " " + currentTile.getPosY());
                    return;
                }
                if (currentTile.getActors().contains(actor)) {
                    currentTile.removeActor(actor);
                    newTile.addActor(actor);
                    actor.setPosition(x, y);
                    return;
                }
                System.out.println("Error! Can't find actor on tile: " + x + ", " + y);
            } else {
                System.out.println("Error! Invalid map or tile at coordinates: " + x + ", " + y);
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
