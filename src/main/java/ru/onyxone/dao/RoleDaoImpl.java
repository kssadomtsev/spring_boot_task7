package ru.onyxone.dao;

import org.springframework.stereotype.Repository;
import ru.onyxone.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl() {
    }


    @Override
    public void create(Role role) {
        entityManager.persist(role);
    }


    @Override
    public Optional<Role> get(String name) {
        return entityManager
                .createQuery("select role from Role role where role.name=:name", Role.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
