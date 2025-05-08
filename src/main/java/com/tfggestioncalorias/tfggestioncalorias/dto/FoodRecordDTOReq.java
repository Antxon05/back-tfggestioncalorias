package com.tfggestioncalorias.tfggestioncalorias.dto;

import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class FoodRecordDTOReq {

    Integer id;

    Integer userId;

    Integer foodId;

    LocalDate date;

    BigDecimal weightgm;



}