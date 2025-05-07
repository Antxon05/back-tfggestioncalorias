package com.tfggestioncalorias.tfggestioncalorias.mapper;


import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor

public class UserMapper {

    public final PasswordEncoder passwordEncoder;

    //Para registrar un usuario, el password se codifica con BCrypt
    public UserApp toEntity(UserRegisterDto userdto){
        UserApp userApp = new UserApp();
        userApp.setName(userdto.getName());
        userApp.setEmail(userdto.getEmail());
        userApp.setPassword(passwordEncoder.encode(userdto.getPassword()));
        userApp.setAge(userdto.getAge());
        userApp.setHeight(userdto.getHeight ());
        userApp.setWeight(userdto.getWeight());
        userApp.setRegistrationDate(LocalDate.now());
        userApp.setGenre(userdto.getGenre());
        userApp.setGoal(userdto.getGoal());
        userApp.setPhisical_activity(userdto.getPhisicalActivity());
        return userApp;
    }

    //Para mostrar la información
    public UserInfoDto toDto(UserApp userApp){
        return UserInfoDto.builder()
                .id(userApp.getId())
                .name(userApp.getName())
                .email(userApp.getEmail())
                .age(userApp.getAge())
                .height(userApp.getHeight())
                .weight(userApp.getWeight())
                .genre(userApp.getGenre())
                .goal(userApp.getGoal())
                .phisicalActivity(userApp.getPhisical_activity())
                .registrationDate(userApp.getRegistrationDate())
                .build();

    }



}
