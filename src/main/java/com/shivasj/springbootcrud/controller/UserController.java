package com.shivasj.springbootcrud.controller;

import com.shivasj.springbootcrud.model.User;
import com.shivasj.springbootcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/users";
    }

    @GetMapping("/add")
    public String getAddUserPage(Model model) {
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteByIdUsers(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String getUserForEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findByIdUsers(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }
}
