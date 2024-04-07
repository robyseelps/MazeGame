package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.ConsoleUI;
import sk.tuke.gamestudio.game.Game;
import sk.tuke.gamestudio.game.Gamefield.MapBuilder;
import sk.tuke.gamestudio.game.Gamestate;
import sk.tuke.gamestudio.service.*;

import java.io.IOException;

@SpringBootApplication
@Configuration
public class SpringClient {

    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class, args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.play();
    }

    @Bean
    public ConsoleUI consoleUI(Game game) {
        return new ConsoleUI(game);
    }

    @Bean
    public Game game() throws IOException {
        return new Game(mapBuilder().buildMap(),Gamestate.PLAYING);
    }

    @Bean
    public MapBuilder mapBuilder(){
        return  new MapBuilder("/maps/hard.txt");
    }
    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }
    @Bean
    public RatingService ratingService(){
        return new RatingServiceJPA();
    }
    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }
}