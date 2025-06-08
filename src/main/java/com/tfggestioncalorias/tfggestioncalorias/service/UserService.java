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

    //SERVICIOS DE USUARIO:


    //Obtenemos un usuario
    public ResponseEntity<UserInfoDTO> getUser(String authHeader){

        String token = authHeader.replace("Bearer", "").trim();
        String email = jwtUtil.extractEmail(token);
        UserApp user = userAppRepository.findByEmail(email);

        UserInfoDTO dto = userMapper.toDto(user);

        return ResponseEntity.ok(dto);

    }

    //Obtenemos el Id del usuario logueado
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

    public Integer getTargetCalories(String authHeader){
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        UserApp user = userAppRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        BigDecimal weight = user.getWeight(); // kg
        Integer height = user.getHeight(); // cm
        Integer age = user.getAge();
        Genre genre = user.getGenre();
        PhisicalActivity activity = user.getPhisical_activity();
        Goal goal = user.getGoal();

        double bmr;
        if (genre == Genre.HOMBRE) {
            bmr = 10 * weight.doubleValue() + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight.doubleValue() + 6.25 * height - 5 * age - 161;
        }

        double activityFactor = switch (activity) {
            case SEDENTARIO -> 1.2;
            case LIGERO -> 1.375;
            case MODERADO -> 1.55;
            case ACTIVO -> 1.725;
            case MUY_ACTIVO -> 1.9;
        };

        double totalCalories = bmr * activityFactor;

        switch (goal) {
            case PERDER  -> totalCalories -= 500;
            case AUMENTAR -> totalCalories += 500;
        }

        return (int) Math.round(totalCalories);
    }


    //Actualizar la información
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
