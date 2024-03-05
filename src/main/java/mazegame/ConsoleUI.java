package mazegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUI implements KeyListener {
    private Game game;
    private List<Integer> keysPressed = new ArrayList<Integer>();

    public ConsoleUI(Game game) {
        this.game = game;
    }

    public void play() {
        show();
        while (game.getGamestate() == Gamestate.PLAYING){
            handleInput();
            show();
        }
        if(game.getGamestate() == Gamestate.WON){
            System.out.flush();
            System.out.println("You won!!");
        }
        else if(game.getGamestate() == Gamestate.FAILED){
            System.out.flush();;
            System.out.println("You lose! :(");
        }
    }

    public void show() {
        System.out.flush();
        int i = 0;
        for (Tile tile : game.getMap().getTileList()){
            if (tile.getTileType() == TileType.WALL){
                System.out.print("X");
            }
            else if(tile.getTileType() == TileType.EMPTY){
                if(tile.getActors().contains(game.getMap().getPlayer()))
                    System.out.print("P");
                else
                    System.out.print(" ");
            } else if (tile.getTileType() == TileType.EXIT) {
                System.out.print("O");
            }
            if (i % game.getMap().getColumnCount() == 0)
                System.out.println();
            i++;
        }
    }
    public void handleInput(){
        Player player = game.getMap().getPlayer();
        for(int key : keysPressed)
            switch (key){
                case KeyEvent.VK_UP:
                    new Move().move(player.getPosX(), player.getPosY()+1,player,game.getMap());
                    break;
                case KeyEvent.VK_DOWN:
                    new Move().move(player.getPosX(), player.getPosY()-1,player,game.getMap());

                    break;
                case KeyEvent.VK_LEFT:
                    new Move().move(player.getPosX()-1, player.getPosY(),player,game.getMap());

                    break;
                case KeyEvent.VK_RIGHT:
                    new Move().move(player.getPosX()+1, player.getPosY()+1,player,game.getMap());
                    break;
            }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // Handle different key presses
        switch (keyCode) {
            case KeyEvent.VK_UP:
                keysPressed.add(KeyEvent.VK_UP);
                break;
            case KeyEvent.VK_DOWN:
                keysPressed.add(KeyEvent.VK_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                keysPressed.add(KeyEvent.VK_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                keysPressed.add(KeyEvent.VK_RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

}
