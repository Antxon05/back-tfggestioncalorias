package com.tfggestioncalorias.tfggestioncalorias.dto.userodtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder(toBuilder = true)
//Lo que inserta el usuario para loguearse
public class UserLoginDTO {
    Integer id;

    @NotEmpty
    @Length(max = 100)
    String email;

    @NotEmpty
    @Length(min = 8)
    String password;
}
