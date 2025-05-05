package com.tfggestioncalorias.tfggestioncalorias.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder(toBuilder = true)
public class UserLoginDto {


    Integer id;


    @NotEmpty
    @Length(max = 100)
    String email;


    @NotEmpty
    @Length(min = 8)
    String password;

}
