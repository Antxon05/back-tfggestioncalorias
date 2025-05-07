package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.config.JwtUtil;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.AuthResponseDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserLoginDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public void register(UserRegisterDto dto){

        if(userAppRepository.findByEmailContaining(dto.getEmail()).isPresent()){
            throw new RuntimeException("El correo ya existe");
        }

        UserApp user = userMapper.toEntity(dto);

        userAppRepository.save(user);
    }

    public AuthResponseDTO login(UserLoginDto dto){
        UserApp user = userAppRepository.findByEmailContaining(dto.getEmail())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token, user.getEmail());

    }


}
