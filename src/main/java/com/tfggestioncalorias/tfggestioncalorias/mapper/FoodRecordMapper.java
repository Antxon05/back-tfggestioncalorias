package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodRecordMapper {

    //Para los GETTERS, sirve para mostrar la información
    public FoodRecordDTO toDto(FoodRecord foodRecord){
        return FoodRecordDTO.builder()
                .id(foodRecord.getId())
                .user(foodRecord.getUser())
                .food(foodRecord.getFood())
                .date(foodRecord.getDate())
                .weightgm(foodRecord.getWeightGm())
                .build();
    }

    //Para los POST y PUT, convierte a entidad para registrar a la base de datos
    public FoodRecord toEntity(FoodRecordDTO foodRecordDto){
        FoodRecord foodRecord = new FoodRecord();
        foodRecord.setUser(foodRecordDto.getUser());
        foodRecord.setFood(foodRecordDto.getFood());
        foodRecord.setDate(foodRecordDto.getDate());
        foodRecord.setWeightGm(foodRecordDto.getWeightgm());
        return foodRecord;
    }

}
