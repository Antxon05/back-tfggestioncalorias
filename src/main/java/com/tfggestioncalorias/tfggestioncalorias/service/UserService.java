package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.config.JwtUtil;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserUpdateDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppRepository userAppRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    //SERVICIOS DE USUARIO:


    //Obtenemos un usuario
    public ResponseEntity<UserInfoDto> getUser(String authHeader){

        String token = authHeader.replace("Bearer", "").trim();
        String email = jwtUtil.extractEmail(token);
        UserApp user = userAppRepository.findByEmail(email);

        UserInfoDto dto = userMapper.toDto(user);

        return ResponseEntity.ok(dto);

    }


    //Actualizar la información
    //todo --> Hacer este metodo correctamente, actualizando el usuario del email proporcionado por el dto
    public String updateUser(String authHeader, UserInfoDto userdto){

        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);

        UserApp user = userAppRepository.findByEmail(email);

        if (user == null) {
            return "Usuario no encontrado";
        }


        UserApp existingUserWithEmail = userAppRepository.findByEmail(userdto.getEmail());

            user.setName(userdto.getName());
            user.setEmail(userdto.getEmail());
            user.setAge(userdto.getAge());
            user.setHeight(userdto.getHeight());
            user.setWeight(userdto.getWeight());
            user.setGenre(userdto.getGenre());
            user.setGoal(userdto.getGoal());
            user.setPhisical_activity(userdto.getPhisicalActivity());
            userAppRepository.save(user);
            return "Usuario actualizado correctamente";


    }

}
