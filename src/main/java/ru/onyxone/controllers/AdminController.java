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
@RequestMapping("/admin")
public class AdminController {
    private final UserManager userManager;

    @Autowired
    public AdminController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userManager.getByEmail(auth.getName()).orElseThrow(() -> new IllegalStateException("User not found"));
        model.addAttribute("currentUser", currentUser);
        return "admin/list";
    }

}
