package ru.onyxone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onyxone.models.Role;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;
import ru.onyxone.utils.Util;

import java.util.Set;

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
        User currentUser = userManager.getByEmail(auth.getName()).get();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userManager.getAll());
        return "admin/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") int id) {
        return userManager.get(id);
    }

//    public String get(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userManager.get(id));
//        return "admin/get";
//    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "admin/new";
//    }

    @PostMapping
    public String create(@ModelAttribute("user") User user, Model model) {
        if (userManager.getByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Util.getRolesByRoleObj(user.getRoles(), userManager));
            //user.setRoles(Util.getRoles(new String[]{"USER"}, userManager));
            userManager.create(user);
        } else {
            model.addAttribute("message", "Email is already used");
        }
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userManager.get(id));
//        return "admin/edit";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Util.getRolesByRoleObj(user.getRoles(), userManager));
        userManager.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userManager.delete(id);
        return "redirect:/admin";
    }
}
