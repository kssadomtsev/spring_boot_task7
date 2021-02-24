package ru.onyxone.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.onyxone.models.User;

import java.util.Optional;

@Repository
@Profile("crud")
public interface UserDaoCrud extends CrudRepository<User, Integer>, UserDao {

    @Query(value = "SELECT u FROM User u JOIN fetch u.roles r WHERE u.id =:id")
    Optional<User> findById(int id);

    @Query(value = "SELECT u FROM User u JOIN fetch u.roles r WHERE u.email =:email")
    Optional<User> findByEmail(String email);
}
