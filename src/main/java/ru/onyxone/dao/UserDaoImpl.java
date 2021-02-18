package ru.onyxone.dao;

import org.springframework.stereotype.Repository;
import ru.onyxone.exception.JpaException;
import ru.onyxone.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }

    @Override
    public Optional<User> get(int id) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN fetch u.roles r WHERE u.id =:id", User.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public void update(User updatedUser) {
        int id = Math.toIntExact(updatedUser.getId());
        User currentUser = get(id).orElseThrow(() -> new IllegalStateException("User not found"));
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());
        entityManager.persist(currentUser);
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        User details = get(id).orElseThrow(() -> new JpaException("User not found!"));
        entityManager.remove(details);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN fetch u.roles r WHERE u.email =:email", User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
