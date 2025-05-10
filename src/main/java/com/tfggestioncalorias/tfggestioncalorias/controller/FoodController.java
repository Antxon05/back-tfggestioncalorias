package com.tfggestioncalorias.tfggestioncalorias.controller;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTO;
import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTOReq;
import com.tfggestioncalorias.tfggestioncalorias.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping()
    public List<FoodDTO> getFoods(@RequestParam(required = false) String name){
        return foodService.getFoods(name);
    }

    @GetMapping("/{id}")
    public Optional<FoodDTO> getFoodById(@PathVariable Integer id){
        return foodService.getFoodById(id);
    }

    @PostMapping("/saveFood")
    public FoodDTO saveFood(@RequestBody FoodDTOReq dto, @RequestHeader("Authorization") String authHeader){
        return foodService.saveFood(dto, authHeader);
    }

    @DeleteMapping("/deleteFood/{id}")
    public Optional<FoodDTO> deleteFood(@PathVariable Integer id, @RequestHeader("Authorization") String authHeader){
        return foodService.deleteFood(id, authHeader);
    }


}
