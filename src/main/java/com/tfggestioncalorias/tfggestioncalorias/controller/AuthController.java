package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.UserLoginDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto request){
        String response = userService.registerUser(request);
        if("Usuario registrado correctamente".equals(response)){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto request){
        String response = userService.loginUser(request.getEmail(), request.getPassword());
        if("Login exitoso".equals(response)){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}
