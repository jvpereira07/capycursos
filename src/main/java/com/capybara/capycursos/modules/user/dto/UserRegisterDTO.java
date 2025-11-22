package com.capybara.capycursos.modules.user.dto;

import com.capybara.capycursos.modules.user.model.Role;
import com.capybara.capycursos.modules.user.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;
}
