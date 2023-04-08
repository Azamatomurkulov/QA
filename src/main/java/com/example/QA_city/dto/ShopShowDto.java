package com.example.QA_city.dto;

import com.example.QA_city.entity.City;
import com.example.QA_city.entity.Street;
import lombok.Data;

import java.time.LocalTime;
@Data
public class ShopShowDto {
    private Long id;

    private String name;

    private String city;

    private String street;

    private String house;

    private LocalTime openingTime;

    private LocalTime closingTime;
}
