package epam.com.esm.model.service.impl;

import epam.com.esm.exception.types.EntityAlreadyExists;
import epam.com.esm.exception.types.EntityNotFoundException;
import epam.com.esm.exception.types.OperationFailedException;
import epam.com.esm.model.repository.impl.RoleRepository;
import epam.com.esm.model.repository.impl.UserRepository;
import epam.com.esm.model.service.UserService;
import epam.com.esm.persistence.entity.impl.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExists("User already exists");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("User not exists");
        }

        if (userRepository.existsByUsername(user.getUsername())
            && !Objects.equals(userRepository.findByUsername(user.getUsername()).get().getId(), user.getId())) {
            throw new OperationFailedException("Username is unavailable");
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public User delete(Long id) {
        User d = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(id);
        return d;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}