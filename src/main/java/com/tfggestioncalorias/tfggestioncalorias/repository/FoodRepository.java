package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.dto.FoodDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.Food;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> getFoodByName(String name);
}
