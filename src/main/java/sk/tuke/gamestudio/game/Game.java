package sk.tuke.gamestudio.game;


import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Gamefield.Map;
import sk.tuke.gamestudio.service.CommentServiceJDBC;
import sk.tuke.gamestudio.service.RatingServiceJDBC;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;

import java.util.Date;

public class Game {
    private Map map;
    private Gamestate gamestate;
    private Score playerScore;
    private Rating rating;
    private Comment comment;
    private ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
    private CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
    private RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
    public Game(Map map, Gamestate gamestate){
        this.map = map;
        this.gamestate = gamestate;
    }

    public Map getMap() {
        return map;
    }

    public Score getPlayerScore() {
        return playerScore;
    }

    public ScoreServiceJDBC getScoreServiceJDBC() {
        return scoreServiceJDBC;
    }

    public void setPlayerScore(Score playerScore) {
        this.playerScore = playerScore;
    }

    public Rating getRating() {
        return rating;
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
            map.updatePlayerCollision();
        }
    }
    public void addComment(String Comment) {
        setComment(new Comment(
                "Maze", map.getPlayer().getPlayerName(), Comment, new Date()
        ));
        commentServiceJDBC.addComment(getComment());
    }
    public void addRating(int rating) {
        setRating(new Rating(
                "Maze", map.getPlayer().getPlayerName(), rating, new Date()
        ));
        ratingServiceJDBC.setRating(getRating());
    }
    public void addScore(){
        setPlayerScore(new Score("Maze",map.getPlayer().getPlayerName(),map.getPlayer().getPlayerScore(),new Date()));
        scoreServiceJDBC.addScore(getPlayerScore());
    }
    public void endGame(){
        if(gamestate != Gamestate.PLAYING && map.getPlayer().getPlayerScore() != 0){
            addScore();
        }
    }
}
