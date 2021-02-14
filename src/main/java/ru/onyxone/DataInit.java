package ru.onyxone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.onyxone.models.User;
import ru.onyxone.services.UserManager;
import ru.onyxone.utils.Util;


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
        admin.setRoles(Util.getRolesByString(new String[]{"ADMIN", "USER"}, userManager));
        this.userManager.create(admin);

        User user = new User("User", "Userov", "user@mail.com", passwordEncoder.encode("user"));
        user.setRoles(Util.getRolesByString(new String[]{"USER"}, userManager));
        this.userManager.create(user);
    }
}
