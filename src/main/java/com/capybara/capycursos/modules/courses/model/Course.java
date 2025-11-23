package com.capybara.capycursos.modules.courses.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="courses")
public class Course {
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="description", nullable = true, length = 2000)
    private String description;
    @OneToMany(mappedBy="course")
    private Set<Module> modules;


}
