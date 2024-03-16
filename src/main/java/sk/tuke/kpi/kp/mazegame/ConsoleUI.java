package sk.tuke.kpi.kp.mazegame;


import sk.tuke.kpi.kp.mazegame.Actions.Move;
import sk.tuke.kpi.kp.mazegame.Actors.Player;
import sk.tuke.kpi.kp.mazegame.Gamefield.Map;
import sk.tuke.kpi.kp.mazegame.Gamefield.Tile;

import java.io.IOException;
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

            handleInput();
            game.updateGame();
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
        clearScreen();
        Map map = game.getMap();
        if (map == null)
            return;

        int playerViewDistanceWidth = player.getViewDistanceX();
        if (playerViewDistanceWidth > map.getColumnCount())
            playerViewDistanceWidth = map.getColumnCount() / 2;

        int playerViewDistanceHeight = player.getViewDistanceY();
        if (playerViewDistanceHeight > map.getRowCount())
            playerViewDistanceHeight = map.getRowCount() / 2;

        int leftMax = Math.max(0, player.getPosX() - playerViewDistanceWidth / 2);
        int rightMax = Math.min(map.getColumnCount(), leftMax + playerViewDistanceWidth);
        int upMax = Math.max(0, player.getPosY() - playerViewDistanceHeight / 2);
        int downMax = Math.min(map.getRowCount(), upMax + playerViewDistanceHeight);
        int tileWidth =  map.getTileList().getFirst().getWidth();
        System.out.print("┌");
        for (int i = 0; i < playerViewDistanceWidth * tileWidth; i++) {
            System.out.print("─");
        }
        System.out.println("┐");
        for (int i = upMax; i < downMax; i++) {
            System.out.print("│");
            for (int j = leftMax; j < rightMax; j++) {
                Tile tile = map.getMapArray()[j][i];
                System.out.print(tile.tileToString());
            }
            for (int k = 0; k < playerViewDistanceWidth - (rightMax - leftMax); k++) {
                System.out.print(" ");
            }
            System.out.println("│");
        }

        // Print the bottom border
        System.out.print("└");
        for (int i = 0; i < playerViewDistanceWidth * tileWidth; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
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
            case "q":
                game.setGamestate(Gamestate.FAILED);
                break;
        }
    }
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
               System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            }
        } catch (IOException | InterruptedException ex) {}
    }

}
