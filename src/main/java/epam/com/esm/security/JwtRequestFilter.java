package epam.com.esm.security;

import epam.com.esm.exception.types.OperationFailedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenGenerator jwtTokenGenerator;

    public JwtRequestFilter(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
                username = jwtTokenGenerator.getUsername(token);
            }  catch (ExpiredJwtException | SignatureException e) {
                throw new OperationFailedException("Token expired or signature corrupted");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenGenerator.getRoles(token)
                                     .stream()
                                     .map(SimpleGrantedAuthority::new)
                                     .collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(upToken);
        }

        filterChain.doFilter(request, response);
    }
}