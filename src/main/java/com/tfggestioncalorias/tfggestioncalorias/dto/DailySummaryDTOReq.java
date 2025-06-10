package com.tfggestioncalorias.tfggestioncalorias.dto;

import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
//Los datos para registrar un nuevo daily summary
public class DailySummaryDTOReq {
    Integer id;

    @NotNull
    Integer user;

    @NotNull
    LocalDate date;

    @NotNull
    Integer consumedCalories;

    @NotNull
    BigDecimal consumedCarbohydrates;

    @NotNull
    BigDecimal consumedProtein;

    @NotNull
    BigDecimal consumedFats;

    @NotNull
    Integer goalCalories;

    @NotNull
    BigDecimal goalCarbohydrates;

    @NotNull
    BigDecimal goalProtein;

    @NotNull
    BigDecimal goalFats;
}
