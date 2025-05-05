package com.tfggestioncalorias.tfggestioncalorias.controller;


import com.tfggestioncalorias.tfggestioncalorias.dto.UserInfoDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //Obtenemos todos los usuarios creados
    @GetMapping()
    public List<UserInfoDto> getAllUsers(){
        return userService.getAllUsers();
    }


    //Obtenemos solo un usuario por email
    @GetMapping("/{email}")
    public UserInfoDto getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }


    //Actualizamos el usuario
    @PutMapping()
    public String updateUser(@Valid @RequestBody UserInfoDto userdto){
        return userService.updateUser(userdto);
    }

}
