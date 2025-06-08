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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class FoodRecordMapper {

    private final UserAppRepository userAppRepository;
    private final FoodRepository foodRepository;

    //Para los GETTERS, sirve para mostrar la información
    public FoodRecordDTO toDto(FoodRecord foodRecord){
        BigDecimal caloriesPer100g = BigDecimal.valueOf(foodRecord.getFood().getCalories());
        Integer realCalories = caloriesPer100g
                .multiply(foodRecord.getWeightGm())
                .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
                .intValue();

        return FoodRecordDTO.builder()
                .id(foodRecord.getId())
                .user(foodRecord.getUser().getId())
                .food(foodRecord.getFood().getId())
                .foodName(foodRecord.getFood().getName())
                .calories(realCalories)
                .date(foodRecord.getDate())
                .weightgm(foodRecord.getWeightGm())
                .dayMoment(foodRecord.getDayMoment())
                .build();
    }

    //Para los POST y PUT, convierte a entidad para registrar a la base de datos
    public FoodRecord toEntity(FoodRecordDTOReq foodRecordDto, Integer userId){
        FoodRecord foodRecord = new FoodRecord();

        UserApp userApp = userAppRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        foodRecord.setUser(userApp);

        Food food = foodRepository.findById(foodRecordDto.getFoodId())
                .orElseThrow(()-> new RuntimeException("Alimento no encontrado"));

        foodRecord.setFood(food);
        foodRecord.setDate(LocalDate.now());
        foodRecord.setWeightGm(foodRecordDto.getWeightgm());
        foodRecord.setDayMoment(foodRecordDto.getDayMoment());
        return foodRecord;
    }

}
