package org.adt.jwtauthtrenning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adt.jwtauthtrenning.dto.AuthenticationRequest;
import org.adt.jwtauthtrenning.dto.AuthenticationResponse;
import org.adt.jwtauthtrenning.dto.RegisterRequest;
import org.adt.jwtauthtrenning.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok().body(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok().body(authenticationService.register(request));
    }
}
