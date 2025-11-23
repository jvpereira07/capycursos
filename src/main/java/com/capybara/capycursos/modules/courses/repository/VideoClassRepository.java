package com.capybara.capycursos.modules.courses.repository;

import com.capybara.capycursos.modules.courses.model.VideoClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoClassRepository extends JpaRepository<VideoClass, UUID> {

}
