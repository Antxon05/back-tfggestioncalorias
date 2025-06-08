package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.config.JwtUtil;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.AuthResponseDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserLoginDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.DailySummaryMapper;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.DailySummaryRepository;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final DailySummaryMapper dailySummaryMapper;
    private final DailySummaryRepository dailySummaryRepository;

    public AuthResponseDTO register(UserRegisterDTO dto){
        if(userAppRepository.findByEmailContaining(dto.getEmail()).isPresent()){
            throw new RuntimeException("El correo ya existe");
        }

        UserApp user = userMapper.toEntity(dto);
        userAppRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDTO(token, user.getEmail());
    }

    public AuthResponseDTO login(UserLoginDTO dto){
        UserApp user = userAppRepository.findByEmailContaining(dto.getEmail())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token, user.getEmail());

    }


}
