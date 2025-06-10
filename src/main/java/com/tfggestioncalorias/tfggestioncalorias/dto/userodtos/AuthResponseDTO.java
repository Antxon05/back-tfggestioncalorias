package com.tfggestioncalorias.tfggestioncalorias.dto.userodtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
//Lo que devuelve una vez logueado o registrado
public class AuthResponseDTO {
    private String token;
    private String email;
}

