package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.dto.DailySummaryDTO;
import com.tfggestioncalorias.tfggestioncalorias.entity.DailySummary;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Integer> {
    Optional<DailySummary> findByUserIdAndDate(Integer userId, LocalDate date);
    List<DailySummary> findByUserId(Integer userId);
}
