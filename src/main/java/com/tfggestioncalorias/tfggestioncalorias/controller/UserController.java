package com.tfggestioncalorias.tfggestioncalorias.controller;


import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDTO;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import com.tfggestioncalorias.tfggestioncalorias.service.UserUpdaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserUpdaterService userUpdaterService;

    //OBTENEMOS información del usuario según su token pasado
    @GetMapping()
    public ResponseEntity<UserInfoDTO> getUser(@RequestHeader("Authorization") String authHeader){
        return userService.getUser(authHeader);
    }

    //OBTENEMOS las calorías que le corresponden al usuario según sus datos
    @GetMapping("/targetCalories")
    public Integer getTargetCalories(@RequestHeader("Authorization") String authHeader){
        return userService.getTargetCalories(authHeader);
    }


    //ACTUALIZAMOS el usuario (también actualiza el daily summary)
    @PostMapping("/update")
    public String updateUser(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UserInfoDTO userdto){
        return userUpdaterService.updateUser(authHeader, userdto);
    }

}
