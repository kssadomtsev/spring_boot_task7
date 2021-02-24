package ru.onyxone.dao;

import ru.onyxone.models.Role;

import java.util.Optional;

public interface RoleDao {
     Role save(Role role);

     Optional<Role> findByName(String name);
}
