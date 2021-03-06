package ru.onyxone.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.onyxone.models.Role;

@Repository
@Profile("crud")
public interface RoleDaoCrud extends CrudRepository<Role, Integer>, RoleDao {


}
