package ro.ubb.thesis.app.config.security.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ro.ubb.thesis.app.domain.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Data
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserEntity extends BaseEntity { // "user" is reserved word in h2

    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
