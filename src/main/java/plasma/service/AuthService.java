package plasma.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import plasma.config.JwtUtil;
import plasma.dto.auth.AuthRequest;
import plasma.dto.auth.AuthResponse;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authenticate;
        authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        String generateToken = jwtUtils.generateToken(authenticate);
        System.out.println(generateToken);
        return new AuthResponse(authRequest.getEmail(), generateToken);
    }
}