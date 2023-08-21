package epam.com.esm.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;

    @Value(("${jwt.lifetime}"))
    private Long lifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("roles", getRoles(userDetails));

        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + lifetime);

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(issuedDate)
                   .setExpiration(expirationDate)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private List<String> getRoles(UserDetails userDetails) {
        return userDetails.getAuthorities()
                          .stream()
                          .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}