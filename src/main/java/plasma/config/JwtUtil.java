package plasma.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import plasma.models.User;

import java.util.Date;


@Component
@AllArgsConstructor
public class JwtUtil {

    private final JwtConfiguration jwtConfig;

    public String generateToken(Authentication authentication) {
        User authInfo = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(authInfo.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (jwtConfig.getExpirationDateAfterDays() * 6500000)))
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean verifyToken(String token) {
        Jwts.parser()
                .setSigningKey(jwtConfig.getKey())
                .parseClaimsJws(token);
        return true;
    }
}