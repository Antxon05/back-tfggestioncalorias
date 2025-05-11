package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.DayMoment;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import com.tfggestioncalorias.tfggestioncalorias.mapper.FoodRecordMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.FoodRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodRecordService {

    private final FoodRecordRepository foodRecordRepository;
    private final FoodRecordMapper foodRecordMapper;
    private final UserService userService;
    private final DailySummaryService dailySummaryService;

    public List<FoodRecordDTO> getFoodRecords(String dayMoment, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        if(dayMoment != null){
            DayMoment dayMomentEnum = DayMoment.valueOf(dayMoment.toUpperCase());

            return foodRecordRepository.findByUserIdAndDayMoment(userId, dayMomentEnum)
                    .stream()
                    .map(foodRecordMapper::toDto)
                    .toList();
        }else {
            return foodRecordRepository.findByUserId(userId)
                    .stream()
                    .map(foodRecordMapper::toDto)
                    .toList();
        }
    }

    public Optional<FoodRecordDTO> getFoodRecordById(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        return foodRecordRepository.findByIdAndUserId(id, userId).map(foodRecordMapper::toDto);
    }

    public Integer getCaloriesByFoodRecord(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId)
                .orElseThrow(()-> new RuntimeException("Comida registrada no encontrada"));

        Food food = foodRecord.getFood();
        BigDecimal grams = foodRecord.getWeightGm();

        BigDecimal caloriesConsumed = grams
                .multiply(BigDecimal.valueOf(food.getCalories()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return caloriesConsumed.intValue();
    }


    //CREAR O ACTUALIZAR FOOD RECORD
    public FoodRecordDTO saveFoodRecord(FoodRecordDTOReq dto, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord;

        if(dto.getId() != null){
            foodRecord = foodRecordRepository.findByIdAndUserId(dto.getId(), userId)
                    .orElseThrow(()-> new RuntimeException("Comida registrada no encontrada"));

            foodRecord.setDate(LocalDate.now());
            foodRecord.setWeightGm(dto.getWeightgm());
            foodRecord.setDayMoment(dto.getDayMoment());

            foodRecord = foodRecordRepository.save(foodRecord);
        }else {
            foodRecord = foodRecordMapper.toEntity(dto, userId);
            foodRecord = foodRecordRepository.save(foodRecord);
        }

        dailySummaryService.updateDailySummary(foodRecord);
        return foodRecordMapper.toDto(foodRecord);
    }

    //ELIMINAR UN FOOD RECORD
    public Optional<FoodRecordDTO> deleteFoodRecord(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId)
                .orElseThrow(()-> new RuntimeException("Comida registrada no encontrada"));

        dailySummaryService.subtractDailySummary(foodRecord);

        foodRecordRepository.delete(foodRecord);
        return Optional.of(foodRecordMapper.toDto(foodRecord));
    }


    //



}
