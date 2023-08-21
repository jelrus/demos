package epam.com.esm.model.service;

import epam.com.esm.persistence.entity.impl.User;

public interface UserService extends BaseService<User>{

    User findByUsername(String username);

    Boolean existsByUsername(String username);
}