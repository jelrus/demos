package epam.com.esm.model.repository.impl;

import epam.com.esm.persistence.entity.impl.User;
import epam.com.esm.model.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}