package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.AuthResponseDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserLoginDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.service.AuthService;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto dto){
        authService.register(dto);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody UserLoginDto dto){
            return ResponseEntity.ok(authService.login(dto));
    }


}
