package com.capybara.capycursos.modules.user.dto;

import com.capybara.capycursos.modules.user.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserGetDTO {
    private UUID id;
    private String username;
    private String email;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}
