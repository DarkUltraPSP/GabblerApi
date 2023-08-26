package app.hesias.gabbler.utils.Payload;

import lombok.Getter;

@Getter
public class JwtAuthenticationResponse {
    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
