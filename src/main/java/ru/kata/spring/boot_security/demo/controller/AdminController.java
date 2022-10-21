package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "/admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", userService.listRoles());
        return "/admin/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        List<String> list_s = user.getRoles().stream().map(r->r.getRole()).collect(Collectors.toList());
        List<Role> list_r = userService.listByRole(list_s);
        user.setRoles(list_r);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.listRoles());
        return "/admin/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        List<String> list_s = user.getRoles().stream().map(r->r.getRole()).collect(Collectors.toList());
        List<Role> list_r = userService.listByRole(list_s);
        user.setRoles(list_r);
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}