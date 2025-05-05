package com.tfggestioncalorias.tfggestioncalorias.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_app")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre",columnDefinition = "genre_type", nullable = false)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal", columnDefinition = "goal_type", nullable = false)
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "phisical_activity", columnDefinition = "phisical_activity_type" , nullable = false)
    private PhisicalActivity phisical_activity;

}