package org.adt.jwtauthtrenning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.adt.jwtauthtrenning.dto.AuthenticationRequest;
import org.adt.jwtauthtrenning.dto.AuthenticationResponse;
import org.adt.jwtauthtrenning.dto.RegisterRequest;
import org.adt.jwtauthtrenning.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "эндпоинт для входа",
            responses = {
                    @ApiResponse(responseCode = "200", description = "успешный вход, возвращается access_token"),
                    @ApiResponse(responseCode = "404", description = "пользователь с таким email не найден"),
                    @ApiResponse(responseCode = "400", description = "некорректный json или некорректное заполнение полей json")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        return ResponseEntity.ok().body(authenticationService.login(request));
    }

    @Operation(
            summary = "эндпоинт для регистрации",
            responses = {
                    @ApiResponse(responseCode = "200", description = "успешная регистрация, возвращается access_token"),
                    @ApiResponse(responseCode = "409", description = "пользователь с таким email уже существует"),
                    @ApiResponse(responseCode = "400", description = "некорректный json или некорректное заполнение полей json")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        return ResponseEntity.ok().body(authenticationService.register(request));
    }
}
