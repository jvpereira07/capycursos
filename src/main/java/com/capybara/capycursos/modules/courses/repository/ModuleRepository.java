package com.capybara.capycursos.modules.courses.repository;

import com.capybara.capycursos.modules.courses.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
