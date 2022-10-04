package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
public class MainController {
    private  UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Отображение пользователей - только для админов
    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main-page";
    }

    //Добавление пользователя
    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-edit-page";
    }

    //Информация о пользователе
    @GetMapping("/user-info/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        User currentUser = userService.getUser(id);
        model.addAttribute("userInfo", currentUser);
        return "user-page";
    }

    //Добавление пользователя
    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    //Удаление пользователя
    @RequestMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

    //Обновление информации пользователя
    @RequestMapping("/update-info/{id}")
    public String userInfo(Model model, @PathVariable("id") Long id) {
        User currentUser = userService.getUser(id);
        model.addAttribute("newUser", currentUser);
        return "user-edit-page";
    }
}