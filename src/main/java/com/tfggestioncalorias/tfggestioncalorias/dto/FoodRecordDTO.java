package com.tfggestioncalorias.tfggestioncalorias.dto;

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
    UserApp user;

    @NotNull
    Food food;

    @NotNull
    LocalDate date;

    @NotNull
    BigDecimal weightgm;



}
