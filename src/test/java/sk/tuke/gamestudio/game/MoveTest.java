package sk.tuke.gamestudio.game;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.Actors.Player;
import sk.tuke.gamestudio.game.Actions.Move;
import sk.tuke.gamestudio.game.Gamefield.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void testMove() {
        Map map = new Map(3, 3);
        Player player = new Player(1, 1);
        map.addActor(player);
        Move move = new Move();
        move.move(1, 0, player, map);
        assertEquals(1, player.getPosX());
        assertEquals(0, player.getPosY());
    }

    @Test
    public void testMoveInvalidPosition() {
        Map map = new Map(3, 3);
        Player player = new Player(0, 0);
        map.addActor(player);
        Move move = new Move();
        move.move(-1, 0, player, map);
        assertEquals(0, player.getPosX());
        assertEquals(0, player.getPosY());
    }
}