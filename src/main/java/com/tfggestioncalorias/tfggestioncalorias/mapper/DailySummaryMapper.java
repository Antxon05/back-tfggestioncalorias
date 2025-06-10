package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import com.tfggestioncalorias.tfggestioncalorias.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DailySummaryMapper {
    private final GoalService goalService;

    //Para GETTERS, convierte una entidad a información que queremos mostrar
    public DailySummaryDTO toDto (DailySummary dailySummaryDto){
        return DailySummaryDTO.builder()
                .id(dailySummaryDto.getId())
                .user(dailySummaryDto.getUser())
                .date(dailySummaryDto.getDate())
                .consumedCalories(dailySummaryDto.getConsumedCalories())
                .goalCalories(dailySummaryDto.getGoalCalories())
                .consumedProtein(dailySummaryDto.getConsumedProtein())
                .goalProtein(dailySummaryDto.getGoalProtein())
                .consumedCarbohydrates(dailySummaryDto.getConsumedCarbohydrates())
                .goalCarbohydrates(dailySummaryDto.getGoalCarbohydrates())
                .consumedFats(dailySummaryDto.getConsumedFats())
                .goalFats(dailySummaryDto.getGoalFats())
                .build();
    }

    //Para POST, crea una entidad nueva del daily
    public DailySummary toEntity (UserApp user){
        DailySummary dailySummary = new DailySummary();
        dailySummary.setUser(user);
        dailySummary.setDate(LocalDate.now());

        //Calcula con servicios todos los datos automáticamente, según información del usuario que se le pasa
        Integer goalCalories = goalService.calculateGoalCalories(user);
        BigDecimal goalProtein = goalService.calculateGoalProtein(goalCalories);
        BigDecimal goalCarbs = goalService.calculateGoalCarbohydrates(goalCalories);
        BigDecimal goalFats = goalService.calculateGoalFats(goalCalories);

        dailySummary.setGoalCalories(goalCalories);
        dailySummary.setGoalProtein(goalProtein);
        dailySummary.setGoalCarbohydrates(goalCarbs);
        dailySummary.setGoalFats(goalFats);

        //Se inicializa a 0 todos los datos
        dailySummary.setConsumedCalories(0);
        dailySummary.setConsumedProtein(BigDecimal.ZERO);
        dailySummary.setConsumedCarbohydrates(BigDecimal.ZERO);
        dailySummary.setConsumedFats(BigDecimal.ZERO);

        return dailySummary;
    }

}
