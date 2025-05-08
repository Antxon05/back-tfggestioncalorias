package com.tfggestioncalorias.tfggestioncalorias.controller;


import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDTO;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //Obtenemos todos los usuarios creados
    @GetMapping()
    public ResponseEntity<UserInfoDTO> getUser(@RequestHeader("Authorization") String authHeader){
        return userService.getUser(authHeader);
    }


    //Actualizamos el usuario
    @PostMapping("/update")
    public String updateUser(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UserInfoDTO userdto){
        return userService.updateUser(authHeader, userdto);
    }

}
