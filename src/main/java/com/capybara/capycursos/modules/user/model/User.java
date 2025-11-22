package com.capybara.capycursos.modules.user.model;

import com.capybara.capycursos.modules.user.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="created_at", nullable = false)
    private LocalDateTime created_at;
    @Column(name="is_active", nullable = false)
    private boolean isActive;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="code")
    )
    Set<Role> userRoles;
    public boolean isPasswordCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder)
    {
       return passwordEncoder.matches(loginRequest.password(),this.password);
    }
}
