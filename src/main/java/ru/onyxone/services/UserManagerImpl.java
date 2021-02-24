package ru.onyxone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onyxone.dao.RoleDao;
import ru.onyxone.dao.UserDao;
import ru.onyxone.exception.JpaException;
import ru.onyxone.models.Role;
import ru.onyxone.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagerImpl implements UserManager {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserManagerImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Optional<User> get(int id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void update(User updatedUser) {
        User user= userDao.findById(updatedUser.getId()).orElseThrow(() -> new JpaException("User not found!"));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        userDao.save(user);
    }

    @Transactional
    @Override
    public void create(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleDao.findByName(name);
    }

    @Transactional
    @Override
    public void createRole(Role role) {
        roleDao.save(role);
    }

    @Transactional
    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
