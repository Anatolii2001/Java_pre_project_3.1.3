package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    public final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = user.getId();
        user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user";
    }
}
