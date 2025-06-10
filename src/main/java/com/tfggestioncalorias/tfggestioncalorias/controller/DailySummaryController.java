package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTO;
import com.tfggestioncalorias.tfggestioncalorias.service.DailySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dailysummary")
@RequiredArgsConstructor
public class DailySummaryController {
    private final DailySummaryService dailySummaryService;

    //OBTENEMOS dailySummaries
    @GetMapping()
    public List<DailySummaryDTO> getDailySummary(@RequestParam(required = false) LocalDate date, @RequestHeader("Authorization") String authHeader){
        return dailySummaryService.getDailySummaries(date, authHeader);
    }

    //OBTENEMOS el dailySummary del día actual (se crean automáticamente con login o register)
    @GetMapping("/today")
    public Optional<DailySummaryDTO> getDailySummaryByToday(@RequestHeader("Authorization") String authHeader){
        return dailySummaryService.getDailySummaryByToday(authHeader);
    }


}
