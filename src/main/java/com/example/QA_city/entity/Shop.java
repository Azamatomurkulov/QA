package com.example.QA_city.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private City city;
    @ManyToOne
    private Street street;

    private String house;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private LocalDateTime rdt;


}
