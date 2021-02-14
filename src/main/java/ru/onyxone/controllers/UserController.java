package ru.onyxone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userManager.getByEmail(auth.getName()).get());
        return "user/user";
    }
}
