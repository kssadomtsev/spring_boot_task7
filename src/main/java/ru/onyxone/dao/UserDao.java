package ru.onyxone.dao;

import ru.onyxone.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public Optional<User> get(int id);

    public List<User> getAll();

    public void update(User updatedUser);

    public void create(User user);

    public void delete(int id);

    public Optional<User> getByEmail(String email);
}
