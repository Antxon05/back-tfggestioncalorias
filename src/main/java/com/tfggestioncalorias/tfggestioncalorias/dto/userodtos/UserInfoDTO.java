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
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class UserInfoDTO {

    Integer id;

    @NotEmpty
    @Length(max = 100)
    String name;

    String email;

    @NotNull
    Integer age;

    @NotNull
    Integer height;

    @NotNull
    BigDecimal weight;

    LocalDate registrationDate;

    @NotNull
    Genre genre;

    @NotNull
    Goal goal;

    @NotNull
    PhisicalActivity phisicalActivity;




}
