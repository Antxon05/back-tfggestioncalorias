package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodMapper {


    //Para los GETTERS, sirve para mostrar la información
    public FoodDTO toDto(Food food){
        return FoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .calories(food.getCalories())
                .carbohydrates(food.getCarbohydrates())
                .fats(food.getFats())
                .protein(food.getProtein())
                .isPersonalized(food.getIsPersonalized())
                .createdbyUserid(food.getCreatedbyUserid())
                .build();
    }

    //Para los POST y PUT, convierte a entidad para registrar a la base de datos
    public Food toEntity(FoodDTO fooddto){
        Food food = new Food();
        food.setName(fooddto.getName());
        food.setCalories(fooddto.getCalories());
        food.setCarbohydrates(fooddto.getCarbohydrates());
        food.setFats(fooddto.getFats());
        food.setProtein(fooddto.getProtein());
        food.setIsPersonalized(fooddto.getIsPersonalized());
        food.setCreatedbyUserid(fooddto.getCreatedbyUserid());
        return food;
    }


}
