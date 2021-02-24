package ru.onyxone.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.onyxone.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Profile("entityManager")
public class RoleDaoEntityManager implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoEntityManager() {
    }


    @Override
    public Role save(Role role) {
        entityManager.persist(role);
        return role;
    }


    @Override
    public Optional<Role> findByName(String name) {
        return entityManager
                .createQuery("select role from Role role where role.name=:name", Role.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
