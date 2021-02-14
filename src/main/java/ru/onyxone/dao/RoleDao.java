package ru.onyxone.dao;

import ru.onyxone.models.Role;

import java.util.Optional;

public interface RoleDao {
    public void create(Role role);

    public Optional<Role> get(String name);
}
