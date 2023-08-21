package epam.com.esm.security;

import epam.com.esm.persistence.entity.impl.User;
import epam.com.esm.model.repository.impl.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return convertToUserDetails(
                userRepository.findByUsername(username).orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
            )
        );
    }

    public org.springframework.security.core.userdetails.User convertToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
             user.getUsername(),
             user.getPassword(),
             user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList())
        );
    }
}