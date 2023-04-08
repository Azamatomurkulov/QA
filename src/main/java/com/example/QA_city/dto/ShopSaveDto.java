package com.example.QA_city.dto;

import com.example.QA_city.entity.City;
import com.example.QA_city.entity.Street;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
@Data
public class ShopSaveDto {
    private Long id;
    @NotBlank
    private String name;
    @Min(value = 0, message = "Значение должно быть больше нуля.")
    private Long cityId;
    @Min(value = 0, message = "Значение должно быть больше нуля.")
    private Long streetId;

    private String house;

    private LocalTime openingTime;

    private LocalTime closingTime;
}
