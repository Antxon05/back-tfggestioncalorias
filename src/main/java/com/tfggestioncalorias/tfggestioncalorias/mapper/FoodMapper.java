package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodMapper {
    private final UserAppRepository userAppRepository;

    //Para GETTERS, muestra datos que queremos de Food
    public FoodDTO toDto(Food food){
        Integer createdByUserId = null;
        if (food.getCreatedByUser() != null) {
            createdByUserId = food.getCreatedByUser().getId();
        }

        return FoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .calories(food.getCalories())
                .carbohydrates(food.getCarbohydrates())
                .fats(food.getFats())
                .protein(food.getProtein())
                .isPersonalized(food.getIsPersonalized())
                .createdByUser(createdByUserId)
                .build();
    }

    //Para POST, convierte a entidad para registrar a la base de datos
    public Food toEntity(FoodDTOReq fooddto, Integer userId){
        Food food = new Food();
        food.setName(fooddto.getName());
        food.setCalories(fooddto.getCalories());
        food.setCarbohydrates(fooddto.getCarbohydrates());
        food.setFats(fooddto.getFats());
        food.setProtein(fooddto.getProtein());

        food.setIsPersonalized(true);

        UserApp userApp = userAppRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        food.setCreatedByUser(userApp);

        return food;
    }


}
