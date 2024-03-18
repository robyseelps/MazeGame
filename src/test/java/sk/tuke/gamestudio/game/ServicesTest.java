package sk.tuke.gamestudio.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class ServicesTest {
    private CommentService commentService;
    private RatingService ratingService;
    private ScoreService scoreService;

    @BeforeEach
    public void setUp() {
        commentService = new CommentServiceJDBC();
        ratingService = new RatingServiceJDBC();
        scoreService = new ScoreServiceJDBC();
    }

    @AfterEach
    public void tearDown() {
        try {
            commentService.reset();
            ratingService.reset();
            scoreService.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAndGetComment() {
        Comment comment = new Comment("TestGame", "TestPlayer", "Test123", new Date());
        try {
            commentService.addComment(comment);
            List<Comment> comments = commentService.getComments("TestGame");
            assertNotNull(comments);
            assertEquals(1, comments.size());
            assertEquals(comment.getComment(), comments.get(0).getComment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetAndGetRating() {
        Rating rating = new Rating("TestGame", "TestPlayer", 5, new Date());
        try {
            ratingService.setRating(rating);
            int retrievedRating = ratingService.getRating("TestGame", "TestPlayer");
            assertEquals(rating.getRating(), retrievedRating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAverageRating() {
        Rating rating1 = new Rating("TestGame", "TestPlayer", 4, new Date());
        Rating rating2 = new Rating("TestGame", "TestPlayer2", 5, new Date());
        try {
            ratingService.setRating(rating1);
            ratingService.setRating(rating2);
            int averageRating = ratingService.getAverageRating("TestGame");
            assertEquals(4, averageRating, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addScore() {
        var date = new Timestamp(new Date().getTime());
        var score = new Score("TestGame", "TestPlayer", 100, date);
        scoreService.reset();
        scoreService.addScore(score);
        var scores = scoreService.getTopScores("TestGame");
        assertEquals(1, scores.size());
        assertEquals("TestPlayer", scores.get(0).getPlayer());
        assertEquals("TestGame", scores.get(0).getGame());
        assertEquals(100, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());
        assertEquals(score.toString(), scores.get(0).toString());
    }

    @Test
    public void getTopScores() {
        var date = new Date();
        scoreService.reset();
        scoreService.addScore(new Score("TestGame", "Johhny", 200, date));
        scoreService.addScore(new Score("TestGame", "Jack", 250, date));
        scoreService.addScore(new Score("TestGame", "Bob", 150, date));

        var scores = scoreService.getTopScores("TestGame");
        assertEquals(3, scores.size());
        assertEquals("Jack", scores.get(0).getPlayer());
        assertEquals(250, scores.get(0).getPoints());
        assertEquals("Johhny", scores.get(1).getPlayer());
        assertEquals(200, scores.get(1).getPoints());
        assertEquals("Bob", scores.get(2).getPlayer());
        assertEquals(150, scores.get(2).getPoints());
    }

}