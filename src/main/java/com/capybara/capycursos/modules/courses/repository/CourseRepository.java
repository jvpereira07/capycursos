package com.capybara.capycursos.modules.courses.repository;

import com.capybara.capycursos.modules.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByCode(String code);
}
