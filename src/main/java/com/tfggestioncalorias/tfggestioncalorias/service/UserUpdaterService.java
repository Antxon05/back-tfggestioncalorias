package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.dto.userodtos.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdaterService {

    private final UserService userService;
    private final DailySummaryService dailySummaryService;

    //Actualiza el usuario y el dailysummary (Se crea aquí para evitar bucle entre userService y dailySummaryService)
    public String updateUser(String authHeader, UserInfoDTO userdto) {
        String result = userService.updateUser(authHeader, userdto);
        dailySummaryService.updateExistsDailySummary(authHeader);
        return result;
    }

}
