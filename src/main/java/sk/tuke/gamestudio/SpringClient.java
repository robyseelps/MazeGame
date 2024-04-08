package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.ConsoleUI;
import sk.tuke.gamestudio.game.Game;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.game.Gamefield.MapBuilder;
import sk.tuke.gamestudio.game.Gamestate;
import sk.tuke.gamestudio.service.*;

import java.io.IOException;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
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
        return new Game();
    }


    @Bean
    public ScoreService scoreService(){
        //return new ScoreServiceJPA();
        return new ScoreServiceRestClient();
    }
    @Bean
    public RatingService ratingService(){
        //return new RatingServiceJPA();
        return new RatingServiceRestClient();
    }
    @Bean
    public CommentService commentService() {
        //return new CommentServiceJPA();
        return new CommentServiceRestClient();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}