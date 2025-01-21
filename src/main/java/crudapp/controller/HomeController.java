package crudapp.controller;


import crudapp.model.User;
import crudapp.sevice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CrudService crudService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("addeduser", new User());
        Iterable<User> users = crudService.getUsers();
        model.addAttribute("users",users);
        return "home";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute(name = "userToDelete") User user) {
        crudService.deleteUser(user);
        return "redirect:/";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute(name = "addedUser") User user) {
        crudService.addUser(user);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updatedUser") User user) {
        crudService.updateUser(user);
        return "redirect:/";
    }
}
