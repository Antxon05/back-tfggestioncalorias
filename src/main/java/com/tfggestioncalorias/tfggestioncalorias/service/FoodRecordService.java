package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTOReq;
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

    public List<FoodRecordDTO> getFoodRecords(String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        return foodRecordRepository.findByUserId(userId)
                .stream()
                .map(foodRecordMapper::toDto)
                .toList();
    }

    public Optional<FoodRecordDTO> getFoodRecordById(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        return foodRecordRepository.findByIdAndUserId(id, userId).map(foodRecordMapper::toDto);
    }

    //Devuelve las calorias de la comida insertada pero con los gramos registrados
    public Integer getConsumedCalories(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId).orElseThrow(()-> new RuntimeException("Comida no encontrada"));
        Food food = foodRecord.getFood();

        Integer calories = food.getCalories();
        BigDecimal gramsConsumed = foodRecord.getWeightGm();

        BigDecimal caloriesConsumed = gramsConsumed
                .multiply(BigDecimal.valueOf(calories)
                .divide(BigDecimal.valueOf(100)));

        return caloriesConsumed.intValue();
    }

    //Nos devuelve las proteínas consumidas basadas en los gramos registrados
    public BigDecimal getConsumedProtein(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId).orElseThrow(()-> new RuntimeException("Comida no encontrada"));
        Food food = foodRecord.getFood();

        BigDecimal protein = food.getProtein();
        BigDecimal gramsConsumed = foodRecord.getWeightGm();

        BigDecimal proteinConsumed = gramsConsumed
                .multiply(protein)
                .divide(BigDecimal.valueOf(100));

        return proteinConsumed.setScale(2, RoundingMode.HALF_UP);
    }

    //Nos devuelve los carbos consumidos basados en los gramos registrados
    public BigDecimal getConsumedCarbohydrates(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId).orElseThrow(()-> new RuntimeException("Comida no encontrada"));
        Food food = foodRecord.getFood();

        BigDecimal carbs = food.getCarbohydrates();
        BigDecimal gramsConsumed = foodRecord.getWeightGm();

        BigDecimal carbsConsumed = gramsConsumed
                .multiply(carbs)
                .divide(BigDecimal.valueOf(100));

        return carbsConsumed.setScale(2, RoundingMode.HALF_UP);
    }


    //Nos devuelve las grasas consumidas basadas en los gramos registrados
    public BigDecimal getConsumedFats(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId).orElseThrow(()-> new RuntimeException("Comida no encontrada"));
        Food food = foodRecord.getFood();

        BigDecimal fats = food.getFats();
        BigDecimal gramsConsumed = foodRecord.getWeightGm();

        BigDecimal fatsConsumed = gramsConsumed
                .multiply(fats)
                .divide(BigDecimal.valueOf(100));

        return fatsConsumed.setScale(2, RoundingMode.HALF_UP);
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

            return foodRecordMapper.toDto(foodRecordRepository.save(foodRecord));
        }else {
            foodRecord = foodRecordMapper.toEntity(dto);
            return foodRecordMapper.toDto(foodRecordRepository.save(foodRecord));
        }
    }

    //ELIMINAR UN FOOD RECORD
    public Optional<FoodRecordDTO> deleteFoodRecord(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        FoodRecord foodRecord = foodRecordRepository.findByIdAndUserId(id, userId)
                .orElseThrow(()-> new RuntimeException("Comida registrada no encontrada"));

        foodRecordRepository.delete(foodRecord);
        return Optional.of(foodRecordMapper.toDto(foodRecord));
    }


    //



}
