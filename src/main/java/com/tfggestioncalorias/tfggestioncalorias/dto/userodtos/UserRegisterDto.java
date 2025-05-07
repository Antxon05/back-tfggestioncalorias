package com.tfggestioncalorias.tfggestioncalorias.dto.userodtos;

import com.tfggestioncalorias.tfggestioncalorias.entity.Genre;
import com.tfggestioncalorias.tfggestioncalorias.entity.Goal;
import com.tfggestioncalorias.tfggestioncalorias.entity.PhisicalActivity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class UserRegisterDto {

    Integer id;


    @NotEmpty
    @Length(max = 100)
    String name;

    @NotEmpty
    @Length(max = 100)
    String email;

    @NotEmpty
    @Length(min = 8)
    String password;

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
