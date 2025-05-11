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


    //obtenemos solo los getters porque ya lo estamos creando mediante el foodRecord
    @GetMapping()
    public List<DailySummaryDTO> getDailySummary(@RequestParam(required = false) LocalDate date, @RequestHeader("Authorization") String authHeader){
        return dailySummaryService.getDailySummaries(date, authHeader);
    }



    @GetMapping("/today")
    public Optional<DailySummaryDTO> getDailySummaryById(@RequestHeader("Authorization") String authHeader){
        return dailySummaryService.getDailySummaryByToday(authHeader);
    }


}
