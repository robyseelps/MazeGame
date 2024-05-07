package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.tuke.gamestudio.game.Actions.Move;
import sk.tuke.gamestudio.game.Actors.Player;
import sk.tuke.gamestudio.game.Game;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.game.Gamefield.MapBuilder;
import sk.tuke.gamestudio.game.Gamefield.Tile;
import sk.tuke.gamestudio.game.Gamestate;


import java.io.IOException;


@Controller
public class GameController {

    @Autowired
    private LoginController loginController;

    @Autowired
    private Game game;

    @GetMapping("/startNewGame")
    public String startNewGame(Model model) throws IOException {
        String playerName = game.getMap().getPlayer().getPlayerName();
        if(playerName == null){
            playerName = loginController.getLoggedUser().getLogin();
        }
        game.setMap(new MapBuilder("/maps/easy.txt").buildMap());
        game.getMap().getPlayer().setPlayerName(playerName);
        game.setGamestate(Gamestate.PLAYING);
        game.setEnded(false);
        String initialMap = generateUpdatedMap();

        model.addAttribute("map", initialMap);
        model.addAttribute("game",game);
        return "startgame";
    }

    @GetMapping("/startGame")
    public String startGame(Model model) {
        try {

            game.getMap().getPlayer().setPlayerName(loginController.getLoggedUser().getLogin());
            String initialMap = generateUpdatedMap();
            model.addAttribute("map", initialMap);
            model.addAttribute("game",game);
            return "startgame";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
    @PostMapping("/move")
    @ResponseBody
    public MapDto move(@RequestBody MoveRequest moveRequest) {
        try {
            String direction = moveRequest.getDirection();
            Map map = game.getMap();
            Player player = map.getPlayer();

            if ("up".equals(direction)) {
                new Move().move(player.getPosX(), player.getPosY() - 1, player, map);
            } else if ("down".equals(direction)) {
                new Move().move(player.getPosX(), player.getPosY() + 1, player, map);

            } else if ("left".equals(direction)) {
                new Move().move(player.getPosX() - 1, player.getPosY(), player, map);

            } else if ("right".equals(direction)) {
                new Move().move(player.getPosX() + 1, player.getPosY(), player, map);

            }

            String updatedMap = generateUpdatedMap();
            MapDto mapDto = new MapDto(updatedMap);
            return mapDto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to process move request: " + e.getMessage());
        }
    }
    private String generateUpdatedMap() {
        try {
            game.updateGame();
            if(game.getGamestate() == Gamestate.WON){
                game.endGame();
                return "You won!";
            }
            else if(game.getGamestate() == Gamestate.FAILED){
                game.endGame();
                return "You failed..";
            }
            Map map = game.getMap();
            Player player = game.getMap().getPlayer();
            int playerViewDistanceWidth = player.getViewDistanceX();
            if (playerViewDistanceWidth > map.getColumnCount())
                playerViewDistanceWidth = map.getColumnCount() / 2;

            int playerViewDistanceHeight = player.getViewDistanceY();
            if (playerViewDistanceHeight > map.getRowCount())
                playerViewDistanceHeight = map.getRowCount() / 2;

            int leftMax = Math.max(0, player.getPosX() - playerViewDistanceWidth / 2);
            int rightMax = Math.min(map.getColumnCount(), leftMax + playerViewDistanceWidth);
            if (rightMax - leftMax < playerViewDistanceWidth) {
                leftMax = Math.max(0, rightMax - playerViewDistanceWidth);
            }
            int upMax = Math.max(0, player.getPosY() - playerViewDistanceHeight / 2);
            int downMax = Math.min(map.getRowCount(), upMax + playerViewDistanceHeight);
            int tileWidth = map.getTileList().get(0).getWidth();
            StringBuilder Out_string = new StringBuilder();
            Out_string.append("Score: ").append(player.getPlayerScore());
            Out_string.append("\n");
            Out_string.append("┌");
            for (int i = 0; i < playerViewDistanceWidth * tileWidth; i++) {
                Out_string.append("─");
            }
            Out_string.append("┐\n");
            for (int i = upMax; i < downMax; i++) {
                Out_string.append("│");
                for (int j = leftMax; j < rightMax; j++) {
                    Tile tile = map.getMapArray()[j][i];
                    Out_string.append(tile.tileToString());
                }
                for (int k = 0; k < playerViewDistanceWidth - (rightMax - leftMax); k++) {
                    Out_string.append(" ");
                }
                Out_string.append("│\n");
            }

            Out_string.append("└");
            for (int i = 0; i < playerViewDistanceWidth * tileWidth; i++) {
                Out_string.append("─");
            }
            Out_string.append("┘\n");

            return Out_string.toString();
        } catch (Exception e) {
            return "error";
        }
    }

    public static class MoveRequest {
        private String direction;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

    public static class MapDto {
        private String map;

        public MapDto(String map) {
            this.map = map;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}


