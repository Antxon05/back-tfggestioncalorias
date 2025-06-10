package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.exceptions.ExistsException;
import com.tfggestioncalorias.tfggestioncalorias.exceptions.UnauthorizeException;
import com.tfggestioncalorias.tfggestioncalorias.mapper.FoodMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.FoodRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;
    private final UserService userService;

    //Si se pasa un name busca por name y si no busca todas las comidas disponibles
    public List<FoodDTO> getFoods(String name){
        if(name != null){
            return foodRepository.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(foodMapper::toDto)
                    .toList();
        }else {
            return foodRepository.findAll()
                    .stream()
                    .map(foodMapper::toDto)
                    .toList();
        }
    }

    //Busca por id de una comida
    public Optional<FoodDTO> getFoodById(Integer id){
        return foodRepository.findById(id).map(foodMapper::toDto);
    }

    //Crea o actualiza si es el atributo de isPersonalized es true y si tiene el mismo id de usuario
    public FoodDTO saveFood(FoodDTOReq dto, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);
        Food food;

        if (dto.getIsPersonalized() == null) {
            dto = dto.toBuilder()
                    .isPersonalized(true)
                    .build();
        }

        //Si el DTO recibido tiene id es para ACTUALIZAR
        if (dto.getId() != null && dto.getIsPersonalized() == true) {
            food = foodRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

            if (food.getCreatedByUser() == null || !food.getCreatedByUser().getId().equals(userId)) {
                throw new RuntimeException("No tienes permiso para modificar este alimento");
            }

            food.setName(dto.getName());
            food.setCalories(dto.getCalories());
            food.setCarbohydrates(dto.getCarbohydrates());
            food.setFats(dto.getFats());
            food.setProtein(dto.getProtein());

            return foodMapper.toDto(foodRepository.save(food));
        } else {
            food = foodMapper.toEntity(dto, userId);
            return foodMapper.toDto(foodRepository.save(food));
        }
    }

    //Elimina si es personalizado por el usuario que se le pasa
    public Optional<FoodDTO> deleteFood(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        Food food = foodRepository.findById(id).orElseThrow(()-> new ExistsException("Food","Alimento no encontrado"));

        if(food.getIsPersonalized() == false){
            throw new UnauthorizeException("Food","No puedes eliminar un alimento que no es personalizado");
        } else if (!food.getCreatedByUser().getId().equals(userId)) {
            throw new UnauthorizeException("Not created by your userId","No puedes eliminar un alimento que no tienes creado por ti");
        } else {
            foodRepository.delete(food);
            return Optional.of(foodMapper.toDto(food));
        }
    }

}
