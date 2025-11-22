package com.capybara.capycursos.modules.user.repository;

import com.capybara.capycursos.modules.user.model.User;
import com.capybara.capycursos.modules.user.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<List<User>> findAllByIsActiveTrue();
    Optional<User> findByUsername(String username);
}
