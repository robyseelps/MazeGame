package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.service.UserService;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Controller
public class LoginController {

    private User loggedUser;

    @Autowired
    private UserService userService;
    @Autowired
    private GameController gameController;

    @GetMapping("/register")
    public String registerRedirect() {
        return "register";
    }


    @GetMapping("/play")
    public String play(Model model) {
        String playerName = loggedUser.getLogin();
        if (playerName == null || playerName.isEmpty()) {
            playerName = "Guest";
        }
        model.addAttribute("playerName", playerName);
        return "play";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String login, @RequestParam String password, Model model) throws IOException {
        String encodedPassword = hashPassword(password);
        User user = userService.getUserFromLoginAndPass(login, encodedPassword);
        if (user != null) {
            if(gameController != null && gameController.getGame() != null){
                gameController.startNewGame(model);
            }
            loggedUser = user;
            return "redirect:/play";
        }
        model.addAttribute("errorMessage", "Invalid login or password");
        return "maze";
    }

    @PostMapping("/register")
    public String userRegister(@RequestParam String login, @RequestParam String password, Model model) {
        User loginExists = userService.getUserFromLogin(login);
        if (loginExists != null) {
            model.addAttribute("errorMessage", "User with this login already exists");
            return "register";
        }

        String encodedPassword = hashPassword(password);
        User newUser = new User(login, encodedPassword);

        try {
            userService.addUser(newUser);
            return "redirect:/?registrationSuccess=true";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to register user. Please try again.");
            return "register";
        }
    }

    @GetMapping("/signout")
    public String userSignOut(Model model) {
        if (loggedUser != null)
            loggedUser = null;
        return "maze";
    }
    @PostMapping("/checkUserExistence")
    @ResponseBody
    public String checkUserExistence(@RequestParam String login) {
        User user = userService.getUserFromLogin(login);
        if (user != null) {
            return "{\"exists\": true}";
        } else {
            return "{\"exists\": false}";
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error hashing password");
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
