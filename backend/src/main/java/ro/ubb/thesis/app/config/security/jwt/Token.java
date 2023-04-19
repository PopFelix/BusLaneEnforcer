package ro.ubb.thesis.app.config.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Token {

    private String accessToken;
    private String refreshToken;
}
