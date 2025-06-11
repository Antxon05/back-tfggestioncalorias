package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.config.JwtUtil;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.Genre;
import com.tfggestioncalorias.tfggestioncalorias.entity.Goal;
import com.tfggestioncalorias.tfggestioncalorias.entity.PhisicalActivity;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAppRepository userAppRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    //Obtenemos un usuario por token
    public ResponseEntity<UserInfoDTO> getUser(String authHeader){

        String token = authHeader.replace("Bearer", "").trim();
        String email = jwtUtil.extractEmail(token);
        UserApp user = userAppRepository.findByEmail(email);

        UserInfoDTO dto = userMapper.toDto(user);

        return ResponseEntity.ok(dto);

    }

    //Obtenemos el id del usuario logueado (para otros métodos)
    public Integer getAuthenticatedUserId(String authHeader){
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        UserApp user = userAppRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("Usuario no encontrado");
        }else{
            return user.getId();
        }
    }

    //Actualiza la información del usuario
    public String updateUser(String authHeader, UserInfoDTO userdto){
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);

        UserApp user = userAppRepository.findByEmail(email);

        if (user == null) {
            return "Usuario no encontrado";
        }

            user.setName(userdto.getName());
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
