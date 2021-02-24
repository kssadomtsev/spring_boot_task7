package ru.onyxone.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.onyxone.exception.JpaException;
import ru.onyxone.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("entityManager")
public class UserDaoEntityManager implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoEntityManager() {
    }

    @Override
    public Optional<User> findById(int id) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN fetch u.roles r WHERE u.id =:id", User.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteById(int id) {
        User details = findById(id).orElseThrow(() -> new JpaException("User not found!"));
        entityManager.remove(details);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN fetch u.roles r WHERE u.email =:email", User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
