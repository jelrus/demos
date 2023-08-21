package epam.com.esm.controller;

import epam.com.esm.security.AuthService;
import epam.com.esm.view.dto.request.SignupDtoRequest;
import epam.com.esm.view.dto.request.UserDtoRequest;
import epam.com.esm.view.dto.response.SignupDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SignupDtoResponse> login(@RequestBody UserDtoRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupDtoResponse> signup(@RequestBody SignupDtoRequest signupDtoRequest) {
        return ResponseEntity.ok(authService.signup(signupDtoRequest));
    }
}