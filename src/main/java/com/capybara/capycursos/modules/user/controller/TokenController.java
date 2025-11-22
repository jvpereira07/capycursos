package com.capybara.capycursos.modules.user.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import com.capybara.capycursos.modules.user.dto.UserRegisterDTO;
import com.capybara.capycursos.modules.user.model.Role;
import com.capybara.capycursos.modules.user.repository.RoleRepository;
import com.capybara.capycursos.modules.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capybara.capycursos.modules.user.dto.LoginRequest;
import com.capybara.capycursos.modules.user.dto.LoginResponse;
import com.capybara.capycursos.modules.user.model.User;
import com.capybara.capycursos.modules.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TokenController {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.email()).orElseThrow(
                () -> new BadCredentialsException("Usuário não encontrado ou credenciais incorretas")
        );
        if(!user.isPasswordCorrect(loginRequest,bCryptPasswordEncoder) || !user.isActive()){
            throw new BadCredentialsException("Usuário não encontrado ou credenciais incorretas");
        }
        var now = Instant.now();
        var expiresIn = 3000L;
        var scopes = user.getUserRoles()
                .stream()
                .map(Role::getCode)
                .collect(Collectors.joining(" "));
        var claims = JwtClaimsSet.builder()
                .issuer("Backend")
                .subject(String.valueOf(user.getId()))
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope",scopes)
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponse(jwtValue,expiresIn));

    }

}
