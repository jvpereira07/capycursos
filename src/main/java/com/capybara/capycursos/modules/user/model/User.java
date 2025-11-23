package com.capybara.capycursos.modules.user.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capybara.capycursos.modules.courses.model.Course;
import com.capybara.capycursos.modules.user.dto.LoginRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    @ManyToMany
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    Set<Course> courses;
    
    public boolean isPasswordCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder)
    {
       return passwordEncoder.matches(loginRequest.password(),this.password);
    }
}
