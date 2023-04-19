package plasma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import plasma.dto.auth.AuthRequest;
import plasma.dto.auth.AuthResponse;
import plasma.service.AuthService;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService service;

    @PostMapping("/login")
    @PermitAll
    public AuthResponse authenticated(@RequestBody AuthRequest request) {
        return service.authenticate(request);
    }

    @GetMapping("/get/user")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'ADMIN', 'TEACHER')")
    public String user() {
        return "User";
    }

    @GetMapping("/get/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String admin() {
        return "Admin";
    }
}