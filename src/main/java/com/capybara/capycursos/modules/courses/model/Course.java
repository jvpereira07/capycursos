package com.capybara.capycursos.modules.courses.model;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.capybara.capycursos.modules.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="code", nullable = false, unique = true)
    private String code;
    @Column(name="description", nullable = true, length = 2000)
    private String description;
    @OneToMany(mappedBy="course")
    private Set<Module> modules;
    
    @ManyToMany(mappedBy = "courses")
    private Set<User> users;


}
