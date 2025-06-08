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

    public List<FoodRecordDTO> getFoodRecords(String dayMoment, LocalDate date, String authHeader) {
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        if (dayMoment != null && date != null) {
            DayMoment dayMomentEnum = DayMoment.valueOf(dayMoment.toUpperCase());
            return foodRecordRepository.findByUserIdAndDateAndDayMoment(userId, date, dayMomentEnum)
                    .stream()
                    .map(foodRecordMapper::toDto)
                    .toList();
        } else if (dayMoment != null) {
            DayMoment dayMomentEnum = DayMoment.valueOf(dayMoment.toUpperCase());
            return foodRecordRepository.findByUserIdAndDayMoment(userId, dayMomentEnum)
                    .stream()
                    .map(foodRecordMapper::toDto)
                    .toList();
        } else if (date != null) {
            return foodRecordRepository.findByUserIdAndDate(userId, date)
                    .stream()
                    .map(foodRecordMapper::toDto)
                    .toList();
        } else {
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

    //ACTUALIZAR UN FOOD RECORD
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

}
