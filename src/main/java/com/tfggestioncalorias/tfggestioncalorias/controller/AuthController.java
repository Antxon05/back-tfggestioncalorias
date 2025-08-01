package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.AuthResponseDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserLoginDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDTO;
import com.tfggestioncalorias.tfggestioncalorias.service.AuthService;
import lombok.RequiredArgsConstructor;
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

    //ENVIAMOS datos de registro (creamos también daily summary)
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserRegisterDTO dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    //ENVIAMOS datos de login (creamos también daily summary)
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody UserLoginDTO dto){
            return ResponseEntity.ok(authService.login(dto));
    }


}
