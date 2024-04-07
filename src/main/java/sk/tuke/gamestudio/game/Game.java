package sk.tuke.gamestudio.game;


import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.service.*;

import java.util.Date;

public class Game {
    private Map map;
    private Gamestate gamestate;
    private Score score;
    private Rating rating;
    private Comment comment;

    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ScoreService scoreService;
 //   private ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
//    private CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
 //   private RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
    public Game(Map map, Gamestate gamestate){
        this.map = map;
        this.gamestate = gamestate;
    }
    public Game(Map map, Gamestate gamestate, ScoreService scoreService, RatingService ratingService, CommentService commentService){
        this.map = map;
        this.gamestate = gamestate;
        this.scoreService = scoreService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Score getPlayerScore() {
        return score;
    }

    public ScoreService getScoreService() {
        return scoreService;
    }

    public void setPlayerScore(Score playerScore) {
        this.score = playerScore;
    }

    public Rating getRating() {
        return rating;
    }
    public int getAverageRating(){
        return ratingService.getAverageRating("Maze");
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }
    public void updateGame() {
        if (getGamestate() == Gamestate.PLAYING) {
            if (map.checkWon()) {
                map.getPlayer().addPlayerScore(100);
                gamestate = Gamestate.WON;
            }
            else if(map.getPlayer().getPlayerHp() <= 0){
                gamestate = Gamestate.FAILED;
            }
            map.updatePlayerCollision();
        }
    }
    public void addComment(String Comment) {
        setComment(new Comment(
                "Maze", map.getPlayer().getPlayerName(), Comment, new Date()
        ));
        commentService.addComment(getComment());
    }
    public void addRating(int rating) {
        setRating(new Rating(
                "Maze", map.getPlayer().getPlayerName(), rating, new Date()
        ));
        ratingService.setRating(getRating());
    }
    public void addScore(){
        setPlayerScore(new Score("Maze",map.getPlayer().getPlayerName(),map.getPlayer().getPlayerScore(),new Date()));
        scoreService.addScore(getPlayerScore());
    }
    public void endGame(){
        if(gamestate != Gamestate.PLAYING && map.getPlayer().getPlayerScore() != 0){
            if(gamestate == Gamestate.WON)
                addScore();
        }
    }
}
