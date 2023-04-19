package plasma.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfiguration {
    private String key;
    private String tokenPrefix;
    private Long expirationDateAfterDays;


    public void setTokenExpirationAfterDays(Long tokenExpirationAfterDays) {
        this.expirationDateAfterDays = tokenExpirationAfterDays * 6500000;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
