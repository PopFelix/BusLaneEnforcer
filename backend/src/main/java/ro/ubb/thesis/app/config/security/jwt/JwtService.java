package ro.ubb.thesis.app.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.ubb.thesis.app.config.security.exceptions.IncorrectPasswordException;
import ro.ubb.thesis.app.config.security.exceptions.RefreshTokenExpiredException;
import ro.ubb.thesis.app.config.security.user.domain.UserEntity;
import ro.ubb.thesis.app.config.security.user.service.UserService;

@Component
public class JwtService {

    private final Key key;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.accessTokenExpiration}")
    private int accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private int refreshTokenExpiration;

    public JwtService(@Value("${jwt.secret}") String secret, UserService userService,
                      PasswordEncoder passwordEncoder) {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public String createToken(String sub, Map<String, Object> claims, Integer millisToExpire) {
        return Jwts.builder()
                .setSubject(sub)
                .addClaims(claims)
                .setExpiration(Date.from(Instant.now().plus(millisToExpire, ChronoUnit.MILLIS)))
                .signWith(key)
                .compact();
    }

    public Map<String, Object> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Token getTokensAtLogin(String username, String password) {
        UserEntity user = userService.findUser(username);

        if (passwordEncoder.matches(password, user.getPassword())) {
            String rolesString = convertAuthoritiesListToString(user);
            Token tokens = new Token();
            tokens.setAccessToken(createToken(user.getUsername(), Map.of("roles", rolesString),
                    accessTokenExpiration));
            tokens.setRefreshToken(createToken(user.getUsername(), Map.of("roles", rolesString),
                    refreshTokenExpiration));
            return tokens;
        } else {
            throw new IncorrectPasswordException("Incorrect password");
        }
    }

    public Token getTokensAtRefresh(String refreshToken) {
        Map<String, Object> claims;
        try {
            claims = parseClaims(refreshToken);
            String username = (String) claims.get("sub");
            UserEntity user = userService.findUser(username);
            String rolesString = convertAuthoritiesListToString(user);
            Token newTokens = new Token();
            newTokens.setAccessToken(
                    createToken(user.getUsername(), Map.of("roles", rolesString),
                            accessTokenExpiration));
            newTokens.setRefreshToken(refreshToken);
            return newTokens;
        } catch (ExpiredJwtException ex) {
            throw new RefreshTokenExpiredException("Expired refresh token");
        }
    }

    private String convertAuthoritiesListToString(UserEntity user) {
        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
