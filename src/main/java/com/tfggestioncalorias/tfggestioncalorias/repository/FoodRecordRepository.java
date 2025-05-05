package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRecordRepository extends JpaRepository<FoodRecord, Integer> {
}
