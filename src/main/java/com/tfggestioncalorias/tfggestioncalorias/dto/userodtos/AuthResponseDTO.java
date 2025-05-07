package com.tfggestioncalorias.tfggestioncalorias.dto.userodtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String email;
}

