package ru.onyxone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;
import ru.onyxone.utils.Util;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class RootController {
    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RootController(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("user") @Valid User user, final BindingResult bindingResult, Model model) {
        boolean isUserExisting = userManager.getByEmail(user.getEmail()).isPresent();
        if (userManager.getByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Util.getRolesByString(new String[]{"USER"}, userManager));
            userManager.create(user);
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Email is already used");
            return "registration";
        }
    }
}
