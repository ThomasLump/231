package root.controller;

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
        return "home";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "new_user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute(name = "delete_user") User user) {
        userService.deleteUser(user);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "update_user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
