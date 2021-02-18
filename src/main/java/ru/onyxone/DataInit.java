package ru.onyxone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.onyxone.models.Role;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;

import java.util.Set;


@Component
public class DataInit implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserManager userManager;

    @Autowired
    public DataInit(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("Admin", "Adminov", "admin@mail.com", passwordEncoder.encode("admin"));
        Role adminRole1 = new Role("ADMIN");
        adminRole1.setUser(admin);
        Role adminRole2 = new Role("USER");
        adminRole2.setUser(admin);
        admin.setRoles(Set.of(adminRole1, adminRole2));
        this.userManager.create(admin);

        User user = new User("User", "Userov", "user@mail.com", passwordEncoder.encode("user"));
        Role userRole = new Role("USER");
        userRole.setUser(user);
        user.setRoles(Set.of(userRole));
        this.userManager.create(user);
    }
}
