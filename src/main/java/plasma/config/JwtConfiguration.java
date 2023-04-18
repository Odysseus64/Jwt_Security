package plasma.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfiguration {
    private String secret_key;
    private String token_prefix;
    private Long expirationDateAfterDays;


    public void setTokenExpirationAfterDays(Long tokenExpirationAfterDays) {
        this.expirationDateAfterDays = tokenExpirationAfterDays * 6500000;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
