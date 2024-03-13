package sk.tuke.kpi.kp.mazegame;

public class Move {
    public void move(int x, int y, Actor actor, Map map) {
        if (isMovePossible(x, y, map)) {
            if (map != null && map.getMapArray() != null && map.getMapArray()[x][y] != null) {
                Tile tile = map.getMapArray()[actor.getPosX()][actor.getPosY()];
                    System.out.println("Checking tile: " + tile.getPosX() + ", " + tile.getPosY());
                    if (tile.getActors().contains(actor)) {
                        tile.removeActor(actor);
                        map.getMapArray()[x][y].addActor(actor);
                        System.out.println("Actor moved to tile: " + x + ", " + y);
                        return;
                    }

                System.out.println("Error! Can't find actor on tile: " + x + ", " + y);
            } else {
                System.out.println("Error! Invalid map or tile at coordinates: " + x + ", " + y);
            }
        } else {
            System.out.println("Error! Move not possible at coordinates: " + x + ", " + y);
        }
    }
    public boolean isMovePossible(int x,int y, Map map){
        if(map!= null && map.getMapArray() != null){
            return map.getMapArray()[x][y].getTileType() != TileType.WALL;
        }
        return false;
    }
}
