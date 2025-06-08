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

    public DailySummary toEntity (UserApp user){
        DailySummary dailySummary = new DailySummary();

        dailySummary.setUser(user);
        dailySummary.setDate(LocalDate.now());

        Integer goalCalories = goalService.calculateGoalCalories(user);
        BigDecimal goalProtein = goalService.calculateGoalProtein(goalCalories);
        BigDecimal goalCarbs = goalService.calculateGoalCarbohydrates(goalCalories);
        BigDecimal goalFats = goalService.calculateGoalFats(goalCalories);

        dailySummary.setGoalCalories(goalCalories);
        dailySummary.setGoalProtein(goalProtein);
        dailySummary.setGoalCarbohydrates(goalCarbs);
        dailySummary.setGoalFats(goalFats);

        dailySummary.setConsumedCalories(0);
        dailySummary.setConsumedProtein(BigDecimal.ZERO);
        dailySummary.setConsumedCarbohydrates(BigDecimal.ZERO);
        dailySummary.setConsumedFats(BigDecimal.ZERO);

        return dailySummary;
    }

}
