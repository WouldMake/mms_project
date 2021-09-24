package br.com.mesttra.project.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public UsernamePasswordAuthenticationToken toAuthToken () {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
