package ru.onyxone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserRestController(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> readAll() {
        final List<User> users = userManager.getAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> readOne(@PathVariable(name = "id") int id) {
        return userManager.get(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        return userManager.getByEmail(user.getEmail())
                .map(user1 -> new ResponseEntity<>(HttpStatus.CONFLICT))
                .orElseGet(() -> {
                            user.setPassword(passwordEncoder.encode(user.getPassword()));
                            user.getRoles().forEach(role -> role.setUser(user));
                            userManager.create(user);
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        }
                );
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().forEach(role -> role.setUser(user));
        userManager.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        userManager.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
