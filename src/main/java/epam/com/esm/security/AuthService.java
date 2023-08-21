package epam.com.esm.security;

import epam.com.esm.exception.types.InputException;
import epam.com.esm.model.service.UserService;
import epam.com.esm.persistence.entity.impl.User;
import epam.com.esm.view.dto.request.SignupDtoRequest;
import epam.com.esm.view.dto.request.UserDtoRequest;
import epam.com.esm.view.dto.response.SignupDtoResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomUserDetailsService customUserDetailsService;

    private final UserService userService;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final AuthenticationManager authenticationManager;

    public AuthService(CustomUserDetailsService customUserDetailsService,
                       UserService userService, JwtTokenGenerator jwtTokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public SignupDtoResponse login(UserDtoRequest userDtoRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDtoRequest.getUsername(), userDtoRequest.getPassword())
        );

        return autoLogin(userDtoRequest.getUsername());
    }

    public SignupDtoResponse signup(SignupDtoRequest signupDtoRequest) {
        if (!signupDtoRequest.getPassword().equals(signupDtoRequest.getConfirmPassword())) {
            throw new InputException("Passwords doesn't match");
        }

        User u = new User();
        u.setUsername(signupDtoRequest.getUsername());
        u.setPassword(signupDtoRequest.getPassword());
        userService.create(u);

        return autoLogin(u.getUsername());
    }

    private SignupDtoResponse autoLogin(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new SignupDtoResponse(username, jwtTokenGenerator.generateToken(userDetails));
    }
}