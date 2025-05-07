package com.tfggestioncalorias.tfggestioncalorias.controller;


import com.tfggestioncalorias.tfggestioncalorias.config.JwtUtil;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserUpdateDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //Obtenemos todos los usuarios creados
    @GetMapping()
    public ResponseEntity<UserInfoDto> getUser(@RequestHeader("Authorization") String authHeader){
        return userService.getUser(authHeader);
    }


    //Actualizamos el usuario
    @PostMapping("/update")
    public String updateUser(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UserInfoDto userdto){
        return userService.updateUser(authHeader, userdto);
    }

}
