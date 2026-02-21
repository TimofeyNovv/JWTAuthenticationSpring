package org.adt.jwtauthtrenning.service;

import lombok.RequiredArgsConstructor;
import org.adt.jwtauthtrenning.dto.AuthenticationRequest;
import org.adt.jwtauthtrenning.dto.AuthenticationResponse;
import org.adt.jwtauthtrenning.dto.RegisterRequest;
import org.adt.jwtauthtrenning.entity.UserEntity;
import org.adt.jwtauthtrenning.entity.UserRole;
import org.adt.jwtauthtrenning.exception.UserAlreadyExistsException;
import org.adt.jwtauthtrenning.exception.UserNotFoundException;
import org.adt.jwtauthtrenning.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user with email - " + request.getEmail() + " not found"));

        String accessToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("user with email - " + request.getEmail() + " already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();

        userRepository.save(userEntity);
        String accessToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
