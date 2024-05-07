package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.game.Game;
import sk.tuke.gamestudio.game.Gamefield.MapBuilder;
import sk.tuke.gamestudio.game.Gamestate;
import sk.tuke.gamestudio.service.*;

import java.io.IOException;

@SpringBootApplication
@Configuration
@EntityScan("sk.tuke.gamestudio.entity")
public class GameStudioServer {
    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class, args);
    }
    // @Bean
   // public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public UserService userService() {
        return new UserServiceJPA();
    }
    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }
    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }
    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }
    @Bean
    public Game game() throws IOException {return gameInit();}
    private Game gameInit() throws IOException {
        System.out.println("initialized game");
        MapBuilder mapBuilder = new MapBuilder("/maps/easy.txt");
        Game game = new Game(mapBuilder.buildMap(), Gamestate.PLAYING);
        String playerName = "Guest";

        return game;
    }
}