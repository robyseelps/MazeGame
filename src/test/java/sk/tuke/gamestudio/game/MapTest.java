package sk.tuke.gamestudio.game;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.Actors.Exit;
import sk.tuke.gamestudio.game.Actors.Key;
import sk.tuke.gamestudio.game.Actors.Player;
import sk.tuke.gamestudio.game.Actors.SpriteColors;
import sk.tuke.gamestudio.game.Gamefield.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

    @Test
    public void testPlayerCollision() {
        Map map = new Map(5, 5);
        Player player = new Player(2, 2);
        map.setPlayer(player);
        map.addActor(player);
        Key key = new Key();
        map.addActor(key);
        assertTrue(map.getPlayerCollision().contains(key));
    }

    @Test
    public void testCheckWon() {
        Map map = new Map(5, 5);
        Player player = new Player(2, 2);
        map.setPlayer(player);
        map.addActor(player);
        Exit exit = new Exit();
        exit.setWorking(true);
        map.addActor(exit);
        assertTrue(map.checkWon());
    }

    @Test
    public void testUpdatePlayerCollision() {
        Map map = new Map(5, 5);
        Player player = new Player(2, 2);
        map.setPlayer(player);
        map.addActor(player);
        Key key = new Key();
        map.addActor(key);
        map.updatePlayerCollision();
        assertTrue(player.getPlayerScore() > 0);
    }

    @Test
    public void testSpriteToString() {
        String symbol = "[O]";
        SpriteColors color = SpriteColors.PURPLE;
        sk.tuke.gamestudio.game.Actors.Sprite sprite = new sk.tuke.gamestudio.game.Actors.Sprite(symbol);
        sprite.setColor(color);
        assertEquals(color.getString() + symbol + SpriteColors.RESET.getString(), sprite.spriteToString());
    }
}
