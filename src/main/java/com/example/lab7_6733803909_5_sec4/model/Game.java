package com.example.lab7_6733803909_5_sec4.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String platform;
    private Double rating;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private Double price;

    @Column(name = "discount_type")
    private String discountType;

    @Transient
    private Double finalPrice;

    public String getDiscountName() {
        if (discountType == null) {
            return "ราคาปกติ";
        }
        switch (discountType.toUpperCase()) {
            case "STUDENT":
                return "ส่วนลดนักศึกษา (10%)";
            case "SEASONAL":
                return "ส่วนลดเทศกาล (20%)";
            case "NONE":
            default:
                return "ราคาปกติ";
        }
    }
}