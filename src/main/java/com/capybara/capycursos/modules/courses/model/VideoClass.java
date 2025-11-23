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
@Table(name="video_classes")
public class VideoClass {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="numero", nullable = false)
    private Long numero;
    @ManyToOne
    @JoinColumn(name="module", nullable = false)
    private Module module;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="minioId", nullable = false)
    private String minioId;
    @OneToMany(mappedBy = "videoClass")
    private Set<File> files;


}
