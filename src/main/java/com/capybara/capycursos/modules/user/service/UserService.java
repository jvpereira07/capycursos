package com.capybara.capycursos.modules.user.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capybara.capycursos.modules.user.model.User;
import com.capybara.capycursos.modules.user.repository.UserRepository;

@Service
public class UserService {
    private UserRepository repo;
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository repo){

        this.repo = repo;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    /**
     * Salva usuários no banco de dados via Repository
     * -"é o C do CRUD, é o C do FUCK"
     * @param user
     * @return
     */
    public User saveUser(User user){
        user.setCreated_at(LocalDateTime.now());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repo.saveAndFlush(user);

        return(savedUser);
    }

    /**
     * Retorna um usuário o banco que possui o determinado email;
     * - "é o R do CRUD, é o F do Fuck"
     * @param email
     * @return
     */
    public User getUserByEmail(String email){
        return repo.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }
    public boolean checkIfEmailNotExists(String email){
        return !repo.findByEmail(email).isEmpty();
    }
    public User getUser(UUID id){
        return repo.findById(id).orElseThrow(
                () -> new RuntimeException("ID não encontrado")
        );
    }


    /**
     * Retorna uma lista com todos os usuarios
     * - "é o R do CRUD, é o F do FUCK"
     * @return
     */
    public List<User> getUsers(){
        return repo.findAllByIsActiveTrue().orElseThrow(
                () -> new RuntimeException("nenhum usuário encontrado")
        );
    }

    /**
     * Edita o usuário no banco de dados;
     * "é o U do CRUD, é o U do FUCK"
     * @param user  não necesariamente deve conter todos os campos de user
     * @param id
     * @return Retorna o usuário atualizado;
     */
    public User updateUser(User user, UUID id){
        User userEntity = getUser(id);
        User userUpdated = User.builder()
                .id(userEntity.getId())
                .username(user.getUsername() != null ? user.getUsername() : userEntity.getUsername())
                .email(user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .password(user.getPassword() != null ? user.getPassword() : userEntity.getPassword())
                .created_at(userEntity.getCreated_at())
                .userRoles(userEntity.getUserRoles())
                .build();
        return repo.saveAndFlush(userUpdated);
    }

    /**
     * Edita o usuário no banco de dados;
     * "é o U do CRUD, é o U do FUCK"
     * @param user  não necesariamente deve conter todos os campos de user
     * @param email
     * @return Retorna o usuário atualizado;
     */
    public User updateByEmail(User user, String email){
        User userEntity = getUserByEmail(email);
        User userUpdated = User.builder()
                .id(userEntity.getId())
                .username(user.getUsername() != null ? user.getUsername() : userEntity.getUsername())
                .email(user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .password(user.getPassword() != null ? user.getPassword() : userEntity.getPassword())
                .created_at(userEntity.getCreated_at())
                .userRoles(userEntity.getUserRoles())
                .build();
        return repo.saveAndFlush(userUpdated);
    }

    /**
     * Deleta permanentemente um usuário. "É o D do CRUD e o K do FUCK"
     * @param id
     */
    public void deleteUser(UUID id){
        repo.deleteById(id);
    }
    /**
     * Desativa um usuário.
     * @param id
     */
    public void disableUser(UUID id){
        User userEntity = getUser(id);
        userEntity.setActive(false);
    }
    /**
     * Desativa um usuário.
     * @param email
     */
    public void disableUserByEmail(String email){
        User userEntity = getUserByEmail(email);
        userEntity.setActive(false);
    }
    /**
     * reativa um usuário.
     * @param id
     */
    public void enableUser(UUID id){
        User userEntity = getUser(id);
        userEntity.setActive(true);
    }
}
