package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDto;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DailySummaryMapper {


    public DailySummaryDto toDto (DailySummaryDto dailySummaryDto){
        return DailySummaryDto.builder()
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

    public DailySummary toEntity (DailySummaryDto dailySummaryDto){
        DailySummary dailySummary = new DailySummary();
        dailySummary.setUser(dailySummaryDto.getUser());
        dailySummary.setDate(dailySummaryDto.getDate());
        dailySummary.setConsumedCalories(dailySummaryDto.getConsumedCalories());
        dailySummary.setGoalCalories(dailySummaryDto.getGoalCalories());
        dailySummary.setConsumedProtein(dailySummaryDto.getConsumedProtein());
        dailySummary.setGoalProtein(dailySummaryDto.getGoalProtein());
        dailySummary.setConsumedCarbohydrates(dailySummaryDto.getConsumedCarbohydrates());
        dailySummary.setGoalCarbohydrates(dailySummaryDto.getGoalCarbohydrates());
        dailySummary.setConsumedFats(dailySummaryDto.getConsumedFats());
        dailySummary.setGoalFats(dailySummaryDto.getGoalFats());
        return dailySummary;
    }

}
