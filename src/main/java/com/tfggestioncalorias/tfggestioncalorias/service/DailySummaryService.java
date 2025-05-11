package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.mapper.DailySummaryMapper;
import com.tfggestioncalorias.tfggestioncalorias.repository.DailySummaryRepository;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailySummaryService {

    private final DailySummaryRepository dailySummaryRepository;
    private final GoalService goalService;
    private final UserService userService;
    private final DailySummaryMapper dailySummaryMapper;


    public List<DailySummaryDTO> getDailySummaries(LocalDate date, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);

        if(date != null){
            return dailySummaryRepository.findByUserIdAndDate(userId, date)
                    .stream()
                    .map(dailySummaryMapper::toDto)
                    .toList();
        }else {
            return dailySummaryRepository.findByUserId(userId)
                    .stream()
                    .map(dailySummaryMapper::toDto)
                    .toList();
        }

    }

    public Optional<DailySummaryDTO> getDailySummaryByToday(String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);
        return dailySummaryRepository.findByUserIdAndDate(userId, LocalDate.now()).map(dailySummaryMapper::toDto);
    }

    /*
    public Optional<DailySummaryDTO> getDailySummaryById(Integer id, String authHeader){
        Integer userId = userService.getAuthenticatedUserId(authHeader);
        return dailySummaryRepository.findByUserIdAndId(userId, id).map(dailySummaryMapper::toDto);
    }
    */


    // Crea o actualiza el daily summary basado en el foodRecord que se pasa
    public void updateDailySummary(FoodRecord foodRecord){
        LocalDate today = foodRecord.getDate();
        Integer userId = foodRecord.getUser().getId();

        Optional<DailySummary> optionalSummary = dailySummaryRepository.findByUserIdAndDate(userId, today);
        DailySummary dailySummary = optionalSummary.orElseGet(DailySummary::new); //Si no existe crea uno nuevo

        BigDecimal grams = foodRecord.getWeightGm();
        Food food = foodRecord.getFood();

        BigDecimal caloriesConsumed = grams.multiply(BigDecimal.valueOf(food.getCalories())).divide(BigDecimal.valueOf(100));
        BigDecimal proteinConsumed = grams.multiply(food.getProtein()).divide(BigDecimal.valueOf(100));
        BigDecimal carbsConsumed = grams.multiply(food.getCarbohydrates()).divide(BigDecimal.valueOf(100));
        BigDecimal fatsConsumed = grams.multiply(food.getFats()).divide(BigDecimal.valueOf(100));

        if(dailySummary.getId() == null){
            dailySummary.setUser(foodRecord.getUser());
            dailySummary.setDate(today);

            //Calcular los objetivos según los datos del usuario
            UserApp user = foodRecord.getUser();
            Integer goalCalories = goalService.calculateGoalCalories(user);
            BigDecimal goalProtein = goalService.calculateGoalProtein(goalCalories);
            BigDecimal goalCarbs = goalService.calculateGoalCarbohydrates(goalCalories);
            BigDecimal goalFats = goalService.calculateGoalFats(goalCalories);

            dailySummary.setGoalCalories(goalCalories);
            dailySummary.setGoalProtein(goalProtein);
            dailySummary.setGoalCarbohydrates(goalCarbs);
            dailySummary.setGoalFats(goalFats);

            dailySummary.setConsumedCalories(caloriesConsumed.intValue());
            dailySummary.setConsumedProtein(proteinConsumed);
            dailySummary.setConsumedCarbohydrates(carbsConsumed);
            dailySummary.setConsumedFats(fatsConsumed);

        }else{
            //Si existe actualizamos los valores ya definidos
            dailySummary.setConsumedCalories(dailySummary.getConsumedCalories() + caloriesConsumed.intValue());
            dailySummary.setConsumedProtein(dailySummary.getConsumedProtein().add(proteinConsumed));
            dailySummary.setConsumedCarbohydrates(dailySummary.getConsumedCarbohydrates().add(carbsConsumed));
            dailySummary.setConsumedFats(dailySummary.getConsumedFats().add(fatsConsumed));
        }

        dailySummaryRepository.save(dailySummary);
    }

    public void subtractDailySummary(FoodRecord foodRecord) {
        LocalDate date = foodRecord.getDate();
        Integer userId = foodRecord.getUser().getId();

        DailySummary summary = dailySummaryRepository.findByUserIdAndDate(userId, date)
                .orElseThrow(() -> new RuntimeException("Resumen diario no encontrado"));

        BigDecimal grams = foodRecord.getWeightGm();
        Food food = foodRecord.getFood();

        //obtiene los datos basados en los gramos del foodRecord
        BigDecimal calories = grams.multiply(BigDecimal.valueOf(food.getCalories())).divide(BigDecimal.valueOf(100));
        BigDecimal protein = grams.multiply(food.getProtein()).divide(BigDecimal.valueOf(100));
        BigDecimal carbs = grams.multiply(food.getCarbohydrates()).divide(BigDecimal.valueOf(100));
        BigDecimal fats = grams.multiply(food.getFats()).divide(BigDecimal.valueOf(100));

        //actualiza el summary si lo encuentra
        summary.setConsumedCalories(summary.getConsumedCalories() - calories.intValue());
        summary.setConsumedProtein(summary.getConsumedProtein().subtract(protein));
        summary.setConsumedCarbohydrates(summary.getConsumedCarbohydrates().subtract(carbs));
        summary.setConsumedFats(summary.getConsumedFats().subtract(fats));

        dailySummaryRepository.save(summary);
    }


}
