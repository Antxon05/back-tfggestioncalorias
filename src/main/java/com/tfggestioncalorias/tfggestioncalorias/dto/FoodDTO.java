package com.tfggestioncalorias.tfggestioncalorias.dto;

import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class FoodDTO {

    Integer id;

    @NotEmpty
    @Length(max = 100)
    String name;

    @NotNull
    Integer calories;

    @NotNull
    BigDecimal carbohydrates;

    @NotNull
    BigDecimal protein;

    @NotNull
    BigDecimal fats;

    Boolean isPersonalized;

    UserApp createdByUser;





}
