package com.tfggestioncalorias.tfggestioncalorias.mapper;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DailySummaryMapper {


    private final UserAppRepository userAppRepository;

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

    public DailySummary toEntity (DailySummaryDTOReq dailySummaryDto){
        DailySummary dailySummary = new DailySummary();

        UserApp userApp = userAppRepository.findById(dailySummaryDto.getUser())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        dailySummary.setUser(userApp);

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
