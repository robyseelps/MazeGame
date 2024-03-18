package sk.tuke.gamestudio.game;



import sk.tuke.gamestudio.game.Actors.Player;
import sk.tuke.gamestudio.game.Actors.SpriteColors;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.game.Actions.Move;
import sk.tuke.gamestudio.game.Gamefield.Tile;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleUI {
    private Game game;
    private Player player;
    private String logo = "\n" +
            SpriteColors.RED.getString() + "   _____                         ________                       \n" +
            SpriteColors.YELLOW.getString() + "  /     \\ _____  ________ ____  /  _____/_____    _____   ____  \n" +
            SpriteColors.GREEN.getString() + " /  \\ /  \\\\__  \\ \\___   // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\ \n" +
            SpriteColors.CYAN.getString() + "/    Y    \\/ __ \\_/    /\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/ \n" +
            SpriteColors.BLUE.getString() + "\\____|__  (____  /_____ \\\\___  >\\______  (____  /__|_|  /\\___  >\n" +
            SpriteColors.PURPLE.getString() + "        \\/     \\/      \\/    \\/        \\/     \\/      \\/     \\/ \n" + SpriteColors.RESET.getString();
    private Map map;
    Scanner moveScanner = new Scanner(System.in);

    public ConsoleUI(Game game) {
        this.game = game;
        this.map = game.getMap();
        this.player = map.getPlayer();
    }

    public void play() {
        System.out.println(logo);
        getPlayerName();
        showMenu();
    }

    public void startGame() {
        show();
        while (game.getGamestate() == Gamestate.PLAYING) {
            handleInput();
            game.updateGame();
            show();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.print("error sleep");
            System.exit(1);
        }
        if (game.getGamestate() == Gamestate.WON) {
            System.out.flush();
            System.out.println("You won!!");
            game.endGame();
        } else if (game.getGamestate() == Gamestate.FAILED) {
            System.out.flush();
            System.out.println("You lose! :(");
            game.endGame();

        }
    }

    private void showMenu() {
        System.out.println("Welcome to the MazeGame, " + player.getPlayerName());
        System.out.println("1. Start Game");
        System.out.println("2. Leave a Comment");
        System.out.println("3. Rate the Game");
        System.out.println("4. Info");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        String menuOption = moveScanner.nextLine();
        switch (menuOption) {
            case "1":
                startGame();
                break;
            case "2":
                game.addComment(leaveComment());
                showMenu();
                break;
            case "3":
                game.addRating(rateGame());
                showMenu();
                break;
            case "4":
                showInfo();
                showMenu();
                break;
            case "5":
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu();
                break;
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
        if (rightMax - leftMax < playerViewDistanceWidth) {
            leftMax = Math.max(0, rightMax - playerViewDistanceWidth);
        }
        int upMax = Math.max(0, player.getPosY() - playerViewDistanceHeight / 2);
        int downMax = Math.min(map.getRowCount(), upMax + playerViewDistanceHeight);
        int tileWidth = map.getTileList().get(0).getWidth();
        System.out.println("Score: " + SpriteColors.BLUE.getString() + player.getPlayerScore() + SpriteColors.RESET.getString());
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

        System.out.print("└");
        for (int i = 0; i < playerViewDistanceWidth * tileWidth; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }

    public void handleInput() {
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
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    private void getPlayerName() {
        System.out.print("Enter you name: ");
        String playerName = moveScanner.nextLine();
        System.out.println("Your name is: " + playerName);
        boolean goodInput = false;
        do {
            System.out.print("Name is correct? yes/no: ");
            String answer = moveScanner.nextLine();
            switch (answer) {
                case ("yes"):
                    goodInput = true;
                    player.setPlayerName(playerName);
                    break;
                case ("no"):
                    goodInput = true;
                    getPlayerName();
                    break;
                default:
                    System.out.println("Invalid answer: write 'yes' or 'no'");
                    break;
            }
        } while (!goodInput);
    }

    private String leaveComment() {
        System.out.print("Write your comment here: ");
        String comment = moveScanner.nextLine();
        System.out.println("Your comment is: " + comment);
        do {
            System.out.print("Leave comment? yes/no : ");
            String answer = moveScanner.nextLine();
            switch (answer) {
                case ("yes"):
                    return comment;
                case ("no"):
                    return leaveComment();
                default:
                    System.out.println("Invalid answer: write 'yes' or 'no'");
                    break;
            }
        } while (true);
    }

    private int rateGame() {
        System.out.print("Rate this game 1-5: ");
        while (!moveScanner.hasNextInt()) {
            System.out.print("Error! Rate this game 1-5: ");
            moveScanner.next();
        }
        int rate = moveScanner.nextInt();
        while (rate < 1 || rate > 5) {
            System.out.print("Error! Rate this game 1-5: ");
            while (!moveScanner.hasNextInt()) {
                System.out.print("Error! Rate this game 1-5: ");
                moveScanner.next();
            }
            rate = moveScanner.nextInt();
        }

        System.out.println("You have rated this game with " + rate + " stars");
        moveScanner.nextLine();
        do {
            System.out.print("Leave rating? yes/no : ");
            String answer = moveScanner.nextLine();
            switch (answer) {
                case ("yes"):
                    return rate;
                case ("no"):
                    return rateGame();
                default:
                    System.out.println("Invalid answer: write 'yes' or 'no'");
                    break;
            }
        } while (true);
    }
    private void showInfo() {
        System.out.println(logo);
        System.out.println(
                "In \"MazeGame,\" maneuver through a maze using 'WASD' keys to locate the true portal among several impostors. " +
                "Keys discovered can shut down fake portals. Gain 20 points for each key and an extra 100 points for discovering the genuine portal. " +
                "Entering a fake portal results in a 60-point deduction, and entering three fake portals leads to losing the game. " +
                "Aim to find the real exit to maximize your points!");
        System.out.print("Enter any key to return to menu: ");
        String menuOption = moveScanner.nextLine();
    }
}