package com.tfggestioncalorias.tfggestioncalorias.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "daily_summary")
public class DailySummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserApp user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "consumed_calories", nullable = false)
    private Integer consumedCalories;

    @Column(name = "goal_calories", nullable = false)
    private Integer goalCalories;

    @Column(name = "consumed_protein", nullable = false)
    private BigDecimal consumedProtein;

    @Column(name = "goal_protein", nullable = false)
    private BigDecimal goalProtein;

    @Column(name = "consumed_carbohydrates", nullable = false)
    private BigDecimal consumedCarbohydrates;

    @Column(name = "goal_carbohydrates", nullable = false)
    private BigDecimal goalCarbohydrates;

    @Column(name = "consumed_fats", nullable = false)
    private BigDecimal consumedFats;

    @Column(name = "goal_fats", nullable = false)
    private BigDecimal goalFats;

}