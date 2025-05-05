package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Integer> {
}
