package org.adt.jwtauthtrenning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adt.jwtauthtrenning.dto.AuthenticationRequest;
import org.adt.jwtauthtrenning.dto.AuthenticationResponse;
import org.adt.jwtauthtrenning.dto.ErrorResponse;
import org.adt.jwtauthtrenning.dto.RegisterRequest;
import org.adt.jwtauthtrenning.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
                    @ApiResponse(responseCode = "200", description = "успешно"),
                    @ApiResponse(responseCode = "404", description = "пользователь не найден", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "некорректный формат JSON или поля не прошли валидацию", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Operation(
            summary = "эндпоинт для регистрации пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "пользователь успешно зарегистрирован"),
                    @ApiResponse(responseCode = "409", description = "пользователь с таки  email уже существует"),
                    @ApiResponse(responseCode = "400", description = "некорректный формат JSON или поля не прошли валидацию", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
