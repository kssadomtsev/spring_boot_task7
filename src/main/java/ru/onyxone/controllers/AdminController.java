package ru.onyxone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userManager.getByEmail(auth.getName()).orElseThrow(() -> new IllegalStateException("User not found"));

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newUser", new User());
        List<User> all = userManager.getAll();
        model.addAttribute("users", all);
        return "admin/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") int id) {
        return userManager.get(id).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user, Model model) {
        if (userManager.getByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().forEach(role -> role.setUser(user));
            userManager.create(user);
        } else {
            model.addAttribute("message", "Email is already used");
        }
        return "redirect:/admin";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().forEach(role -> role.setUser(user));
        userManager.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userManager.delete(id);
        return "redirect:/admin";
    }
}
