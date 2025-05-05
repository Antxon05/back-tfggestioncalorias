package com.tfggestioncalorias.tfggestioncalorias.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "carbohydrates", nullable = false)
    private BigDecimal carbohydrates;

    @Column(name = "protein", nullable = false)
    private BigDecimal protein;

    @Column(name = "fats", nullable = false)
    private BigDecimal fats;

    @Column(name = "is_personalized", nullable = false)
    private Boolean isPersonalized = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby_userid")
    private UserApp createdbyUserid;

}