package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.tuke.gamestudio.service.UserService;

@Controller
public class MazeController {

    @Autowired
    private LoginController loginController;

    @RequestMapping("/")
    public String maze(@RequestParam(value = "command", required = false) String command,
                       @RequestParam(value = "row", required = false) String row,
                       @RequestParam(value = "column", required = false) String column,
                       Model model) {

        // Check if a user is logged in
        if (loginController.getLoggedUser() != null) {
            // User is logged in, return "startgame" view
            model.addAttribute("command", command);
            model.addAttribute("row", row);
            model.addAttribute("column", column);
            return "play";
        } else {
            // User is not logged in, return "maze" view
            return "maze";
        }
    }
}
