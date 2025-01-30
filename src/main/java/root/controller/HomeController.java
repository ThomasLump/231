package root.controller;

import org.springframework.web.bind.annotation.RequestParam;
import root.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import root.service.UserService;

@Controller
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        System.out.println(userService.getUsers());
        model.addAttribute("userlist", userService.getUsers());
        model.addAttribute("newuser", new User());
        model.addAttribute("updateuser", new User());
        return "home";
    }

    @PostMapping("/add")
    public String add(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") long userId) {
        System.out.println("controller delete " + userId);
        userService.deleteUserById(userId);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateuser") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
