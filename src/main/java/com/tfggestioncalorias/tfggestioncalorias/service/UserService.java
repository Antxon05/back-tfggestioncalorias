package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.UserInfoDto;
import com.tfggestioncalorias.tfggestioncalorias.dto.UserRegisterDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.UserMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppRepository userAppRepository;
    private final UserMapper userMapper;

    //SERVICIOS DE USUARIO:

    //Obtener todos los usuarios
    public List<UserInfoDto> getAllUsers(){
        return userAppRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    //Obtenemos un usuario
    public UserInfoDto getUserByEmail(String email){
        Optional<UserApp> user = userAppRepository.findByEmailContaining(email);
        return user.map(userMapper::toDto).orElse(null);
    }

    //Creación de usuario
    public String registerUser(UserRegisterDto userdto){
        if(userAppRepository.findByEmailContaining(userdto.getEmail()).isPresent()){
            return "Este usuario ya existe";
        }else {
            userAppRepository.save(userMapper.toEntity(userdto));
            return "Usuario registrado correctamente";
        }
    }


    //Actualizar la información
    public String updateUser(UserInfoDto userdto){
        Optional<UserApp> user = userAppRepository.findById(userdto.getId());

        if(user.isEmpty()){
            return null;
        }else if(userAppRepository.findByEmailContaining(userdto.getEmail()).isPresent()){
            return "Este correo ya esta asignado a otro usuario";
        }else{
            user.get().setName(userdto.getName());
            user.get().setEmail(userdto.getEmail());
            user.get().setAge(userdto.getAge());
            user.get().setHeight(userdto.getHeight());
            user.get().setWeight(userdto.getWeight());
            user.get().setGenre(userdto.getGenre());
            user.get().setGoal(userdto.getGoal());
            user.get().setPhisical_activity(userdto.getPhisicalActivity());
            userAppRepository.save(user.get());
            return "Usuario actualizado correctamente";
        }
    }

    //Login del usuario
    public String loginUser(String email, String password){
        Optional<UserApp> useropt = userAppRepository.findByEmailContaining(email);

        if(useropt.isPresent()){
            UserApp userApp = useropt.get();
            if(userMapper.passwordEncoder.matches(password, userApp.getPassword())){
                return "Login exitoso";
            }else{
                return "Contraseña incorrecta";
            }
        }else {
            return "Usuario no encontrado";
        }
    }

}
