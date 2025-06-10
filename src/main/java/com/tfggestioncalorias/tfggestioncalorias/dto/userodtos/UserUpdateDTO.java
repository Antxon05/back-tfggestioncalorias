package com.tfggestioncalorias.tfggestioncalorias.dto.userodtos;

import com.tfggestioncalorias.tfggestioncalorias.entity.Genre;
import com.tfggestioncalorias.tfggestioncalorias.entity.Goal;
import com.tfggestioncalorias.tfggestioncalorias.entity.PhisicalActivity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
//Información que se le pasa para actualizar información del usuario
public class UserUpdateDTO {
    Integer id;

    @NotEmpty
    @Length(max = 100)
    String name;

    @NotNull
    Integer age;

    @NotNull
    Integer height;

    @NotNull
    BigDecimal weight;

    @NotNull
    Genre genre;

    @NotNull
    Goal goal;

    @NotNull
    PhisicalActivity phisicalActivity;
}
