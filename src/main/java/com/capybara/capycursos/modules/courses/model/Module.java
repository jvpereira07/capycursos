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
@Table(name="modules")
public class Module {
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="description", nullable = true, length = 2000)
    private String description;
    @ManyToOne
    @JoinColumn(name="course", nullable = false)
    private Course course;
    @OneToMany(mappedBy="module")
    private Set<VideoClass> videoClasses;


}
