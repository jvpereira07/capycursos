package com.capybara.capycursos.modules.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capybara.capycursos.modules.user.dto.LoginResponse;
import com.capybara.capycursos.modules.user.dto.UserGetDTO;
import com.capybara.capycursos.modules.user.dto.UserRegisterDTO;
import com.capybara.capycursos.modules.user.dto.UserSetDTO;
import com.capybara.capycursos.modules.user.mapper.UserMapper;
import com.capybara.capycursos.modules.user.model.Role;
import com.capybara.capycursos.modules.user.model.User;
import com.capybara.capycursos.modules.user.service.RoleService;
import com.capybara.capycursos.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper mapper;


    @PostMapping
    public ResponseEntity<UserGetDTO> saveUser(@RequestBody UserSetDTO user){
        User savedUser = userService.saveUser(mapper.toEntity(user));
        return ResponseEntity.ok(mapper.toDTO(savedUser));
    }
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<UserGetDTO>> getUsers(){
        return ResponseEntity.ok(mapper.ListToDTO(userService.getUsers()));
    }
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/{email}")
    public ResponseEntity<UserGetDTO> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(mapper.toDTO(userService.getUserByEmail(email)));
    }
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @DeleteMapping
    public ResponseEntity<Void> DisableUserByEmail(@RequestParam String email){
        userService.disableUserByEmail(email);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @PutMapping ResponseEntity<Void> UpdateUserByEmail(@RequestParam String email, @RequestBody UserSetDTO updatedUser){
        userService.updateByEmail(mapper.toEntity(updatedUser),email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse>register(@RequestBody UserRegisterDTO registerRequest){
        if(userService.checkIfEmailNotExists(registerRequest.getEmail())){ // check if this email were registered yet
           return ResponseEntity.badRequest().build();
        }
        Role role = roleService.getRoleByCode("client");
        User user = mapper.toEntity(registerRequest,role);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }


}
