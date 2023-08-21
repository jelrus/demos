package epam.com.esm.model.repository.impl;

import epam.com.esm.persistence.entity.impl.Role;
import epam.com.esm.model.repository.BaseRepository;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(String name);
}