package com.capybara.capycursos.modules.user.config;

import com.capybara.capycursos.modules.user.model.Role;
import com.capybara.capycursos.modules.user.model.User;
import com.capybara.capycursos.modules.user.repository.RoleRepository;
import com.capybara.capycursos.modules.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration

public class AdminUserConfig implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.email}")
    private String adminEmail;
    @Override
    public void run(String... args) throws Exception{
        Role roleAdmin = roleRepository.findByCode("admin");
        User userAdmin = userRepository.findByUsername("admin")
                .orElseGet(
                        () -> {
                            User user = new User();
                            user.setUsername(adminUsername);
                            user.setPassword(passwordEncoder.encode(adminPassword));
                            user.setEmail(adminEmail);
                            user.setUserRoles(Set.of(roleAdmin));
                            user.setCreated_at(LocalDateTime.now());
                            user.setActive(true);
                            return userRepository.save(user);
                        });

    }


}
