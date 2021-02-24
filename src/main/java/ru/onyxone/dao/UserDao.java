package ru.onyxone.dao;

import ru.onyxone.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(int id);

    List<User> findAll();

    User save(User user);

    void deleteById(int id);

    Optional<User> findByEmail(String email);
}
