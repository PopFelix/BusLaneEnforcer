package ro.ubb.thesis.app.config.security.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.thesis.app.config.security.jwt.JwtService;
import ro.ubb.thesis.app.config.security.jwt.Token;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final JwtService jwtService;


    @PostMapping("/login")
    public Token loginUser(String username, String password) {
        return jwtService.getTokensAtLogin(username, password);
    }

    @PostMapping("/refresh")
    public Token refreshAccessToken(@RequestBody String refreshToken) {
        return jwtService.getTokensAtRefresh(refreshToken);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile() {
        return "profile page";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin page";
    }

}
