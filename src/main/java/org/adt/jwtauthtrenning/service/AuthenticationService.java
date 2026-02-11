package org.adt.jwtauthtrenning.service;

import lombok.RequiredArgsConstructor;
import org.adt.jwtauthtrenning.dto.AuthenticationRequest;
import org.adt.jwtauthtrenning.dto.AuthenticationResponse;
import org.adt.jwtauthtrenning.dto.RegisterRequest;
import org.adt.jwtauthtrenning.entity.Role;
import org.adt.jwtauthtrenning.entity.UserEntity;
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


    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user with email - " + request.getEmail() + " not found"));

        var accessToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .access_token(accessToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request){

        if (userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("пользователь с email - " + request.getEmail() + " уже существует");
        }
        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(userEntity);

        var accessToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .access_token(accessToken)
                .build();
    }
}
