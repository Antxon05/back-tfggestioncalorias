package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.repository.FoodRepository;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class FoodRecordMapper {

    private final UserAppRepository userAppRepository;
    private final FoodRepository foodRepository;

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
    public FoodRecord toEntity(FoodRecordDTOReq foodRecordDto){
        FoodRecord foodRecord = new FoodRecord();

        UserApp userApp = userAppRepository.findById(foodRecordDto.getUserId())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        foodRecord.setUser(userApp);

        Food food = foodRepository.findById(foodRecordDto.getFoodId())
                .orElseThrow(()-> new RuntimeException("Alimento no encontrado"));

        foodRecord.setFood(food);
        foodRecord.setDate(LocalDate.now());
        foodRecord.setWeightGm(foodRecordDto.getWeightgm());
        return foodRecord;
    }

}
