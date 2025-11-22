package com.capybara.capycursos.modules.user.dto;

import com.capybara.capycursos.modules.user.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserSetDTO {
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
}
