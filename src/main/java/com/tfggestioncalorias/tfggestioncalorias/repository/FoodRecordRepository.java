package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.entity.DayMoment;
import com.tfggestioncalorias.tfggestioncalorias.entity.FoodRecord;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FoodRecordRepository extends JpaRepository<FoodRecord, Integer> {
    List<FoodRecord> findByUserId(Integer userId);
    Optional<FoodRecord> findByIdAndUserId(Integer id, Integer userId);
    List<FoodRecord> findByUserIdAndDayMoment(Integer userId, DayMoment dayMoment);
    Integer user(UserApp user);
    List<FoodRecord> findByUserIdAndDateAndDayMoment(Integer userId, LocalDate date, DayMoment dayMomentEnum);
    List<FoodRecord> findByUserIdAndDate(Integer userId, LocalDate date);
}
