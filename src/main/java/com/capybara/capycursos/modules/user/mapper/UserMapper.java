package com.capybara.capycursos.modules.user.mapper;

import com.capybara.capycursos.modules.user.dto.UserRegisterDTO;
import com.capybara.capycursos.modules.user.model.Role;
import org.springframework.stereotype.Component;

import com.capybara.capycursos.modules.user.dto.UserGetDTO;
import com.capybara.capycursos.modules.user.dto.UserSetDTO;
import com.capybara.capycursos.modules.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class UserMapper {
    public User toEntity(UserSetDTO dto){
        User user = new User();
        user.setUserRoles(dto.getRoles());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
    public UserGetDTO toDTO(User user){
        UserGetDTO dto = new UserGetDTO();
        dto.setRoles(user.getUserRoles());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreated_at());
        dto.setUsername(user.getUsername());
        dto.setId(user.getId());
        return dto;

    }
    public List<UserGetDTO> ListToDTO(List<User> users){
        List<UserGetDTO> lista = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            lista.add(toDTO(users.get(i)));
        }

        return lista;
    }
    public User toEntity(UserRegisterDTO dto, Role role){
        User user = new User();
        user.setUserRoles(Set.of(role));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
