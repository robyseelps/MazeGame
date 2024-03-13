package sk.tuke.kpi.kp.mazegame;


import java.util.Scanner;

public class ConsoleUI {
    private Game game;
    private Player player;
    private Map map;
    Scanner moveScanner = new Scanner(System.in);

    public ConsoleUI(Game game) {
        this.game = game;
        this.map = game.getMap();
        this.player = map.getPlayer();
    }

    public void play() {
        show();
        while (game.getGamestate() == Gamestate.PLAYING) {
            synchronized (this) {
                try {
                    wait(100);
                } catch (InterruptedException ignored) {
                }
            }
            handleInput();
            show();
        }
        if (game.getGamestate() == Gamestate.WON) {
            System.out.flush();
            System.out.println("You won!!");
        } else if (game.getGamestate() == Gamestate.FAILED) {
            System.out.flush();
            System.out.println("You lose! :(");
        }
    }

    public void show() {
        Map map = game.getMap();
        if(map == null)
            return;
       for(int i = 0; i < map.getRowCount(); i++){
           for(int j = 0; j < map.getColumnCount(); j++){
               Tile tile = map.getMapArray()[j][i];
               if (tile.getTileType() == TileType.WALL)
                   System.out.print("X");
               else if(tile.getTileType() == TileType.ACTOR)
                       System.out.print("P");
               else if (tile.getTileType() == TileType.EMPTY)
                       System.out.print(" ");
               else if (tile.getTileType() == TileType.EXIT)
                   System.out.print("O");
           }
           System.out.println();
       }
    }
    public void handleInput(){
        String playerInput = moveScanner.next();
        switch (playerInput) {
            case "w":
                new Move().move(player.getPosX(), player.getPosY() - 1, player, map);
                break;
            case "s":
                new Move().move(player.getPosX(), player.getPosY() + 1, player, map);
                break;
            case "a":
                new Move().move(player.getPosX() - 1, player.getPosY(), player, map);
                break;
            case "d":
                new Move().move(player.getPosX() + 1, player.getPosY(), player, map);
                break;
        }
    }

}
