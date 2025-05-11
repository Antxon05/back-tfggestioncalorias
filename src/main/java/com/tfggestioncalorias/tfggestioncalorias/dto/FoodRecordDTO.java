package com.tfggestioncalorias.tfggestioncalorias.dto;

import com.tfggestioncalorias.tfggestioncalorias.entity.DayMoment;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class FoodRecordDTO {

    Integer id;

    @NotNull
    Integer user;

    @NotNull
    Integer food;

    String foodName;

    @NotNull
    LocalDate date;

    @NotNull
    BigDecimal weightgm;

    @NotNull
    DayMoment dayMoment;

}
