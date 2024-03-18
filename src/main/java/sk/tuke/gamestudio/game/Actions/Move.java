package sk.tuke.gamestudio.game.Actions;

import sk.tuke.gamestudio.game.Actors.AbstractActor;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.game.Gamefield.Tile;
import sk.tuke.gamestudio.game.Gamefield.TileType;

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
        if(map!= null && map.getMapArray() != null && (x >= 0 && x <= map.getColumnCount()) && ( y >= 0 && y <= map.getRowCount())){
            return map.getMapArray()[x][y].getTileType() != TileType.WALL;
        }
        return false;
    }
}
