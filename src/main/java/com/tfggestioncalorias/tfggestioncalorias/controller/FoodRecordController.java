package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodRecordDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.service.FoodRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foodrecord")
@RequiredArgsConstructor
public class FoodRecordController {

    private final FoodRecordService foodRecordService;

    @GetMapping()
    public List<FoodRecordDTO> getFoodRecords(@RequestParam(required = false) String dayMoment, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestHeader("Authorization") String authHeader){
        if(date == null){
            date = LocalDate.now();
        }

        return foodRecordService.getFoodRecords(dayMoment, date, authHeader);
    }

    @GetMapping("/{id}")
    public Optional<FoodRecordDTO> getFoodRecordById(@PathVariable Integer id, @RequestHeader("Authorization") String authHeader){
        return foodRecordService.getFoodRecordById(id, authHeader);
    }

    //También crea el dailySummary y lo actualiza
    @PostMapping("/saveFoodRecord")
    public FoodRecordDTO saveFoodRecord(@RequestBody FoodRecordDTOReq dto, @RequestHeader("Authorization") String authHeader){
        return foodRecordService.saveFoodRecord(dto, authHeader);
    }

    @DeleteMapping("/deleteFoodRecord/{id}")
    public Optional<FoodRecordDTO> deleteFoodRecord(@PathVariable Integer id, @RequestHeader("Authorization") String authHeader){
        return foodRecordService.deleteFoodRecord(id, authHeader);
    }

}
