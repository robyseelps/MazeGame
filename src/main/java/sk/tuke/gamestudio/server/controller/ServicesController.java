package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.entity.User;

import java.util.Date;
import java.util.List;
@Controller
public class ServicesController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private LoginController loginController;

    @GetMapping("/comments")
    public String showComments(Model model) {
        int rating;
        if (( rating = ratingService.getAverageRating("maze") )!= 0) {
            model.addAttribute("avgRating", rating);
        }

        if(loginController.getLoggedUser() != null){
            model.addAttribute("user",loginController.getLoggedUser());
        }
        List<Comment> commentList = commentService.getComments("maze");
        model.addAttribute("comments", commentList);
        return "comments";
    }
    @GetMapping("/topScores")
    public String topScores(Model model) {
        List<Score> topScores = scoreService.getTopScores("maze");
        model.addAttribute("topScores", topScores);
        return "topScores";
    }
    @GetMapping("/rating")
    public String showRating(Model model) {

        if(loginController.getLoggedUser() != null){
            User loggedUser = loginController.getLoggedUser();
            model.addAttribute("user",loginController.getLoggedUser());
            int userRating = 0;
            try { Integer rating = ratingService.getRating("maze", loggedUser.getLogin());
                if (rating != null) {
                    userRating = rating;
                }
            } catch (Exception ignored) {
            }
            model.addAttribute("userRating",userRating);


        }
        int avgRating = ratingService.getAverageRating("maze");
        model.addAttribute("avgRating",avgRating);
        return "rating";
    }
    @PostMapping("/setRating")
    public String setRating(@RequestParam String player,@RequestParam int rating,Model model) {
        Rating userRating = new Rating("maze",player,rating,new Date());
        ratingService.setRating(userRating);
        return "redirect:/rating";
    }


    @GetMapping("/info")
    public String info(Model model) {
        return "info";
    }
    @PostMapping("/leaveComment")
    public String leaveComment(@RequestParam String player,String comment,Model model) {
        Comment playerComment = new Comment("maze",player,comment,new Date());
        commentService.addComment(playerComment);
        return "redirect:/comments";
    }
}

