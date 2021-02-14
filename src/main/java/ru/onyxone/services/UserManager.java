package ru.onyxone.services;

import ru.onyxone.models.Role;
import ru.onyxone.models.User;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    public User get(int id);

    public List<User> getAll();

    public void update(User updatedUser);

    public void create(User user);

    public void delete(int id);

    public Optional<Role> getRoleByName(String name);

    public void createRole(Role role);

    public Optional<User> getByEmail(String email);
}
