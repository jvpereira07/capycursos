package com.capybara.capycursos.modules.courses.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="class_files")
public class File {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="minioId" , nullable = false)
    private String minioId;
    @ManyToOne
    @JoinColumn(name="videoClass",nullable = false)
    private VideoClass videoClass;


}
