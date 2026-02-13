package org.adt.jwtauthtrenning.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok().body("pong");
    }

    @SecurityRequirement(name = "jwtAuth")
    @GetMapping("/authping")
    public ResponseEntity<String> authping(){
        return ResponseEntity.ok().body("auth pong");
    }
}