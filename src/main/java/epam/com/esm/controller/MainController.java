package epam.com.esm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class MainController {

    @GetMapping("/unsecured")
    public ResponseEntity<String> unsecured() {
        return ResponseEntity.ok().body("unsecured");
    }

    @GetMapping("/secured")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok().body("secured");
    }

    @GetMapping("/admin")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok().body("admin");
    }

    @GetMapping("/info")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<String> info() {
        return ResponseEntity.ok().body("info");
    }
}