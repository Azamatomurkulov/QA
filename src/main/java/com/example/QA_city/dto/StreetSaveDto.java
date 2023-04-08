package com.example.QA_city.dto;

import com.example.QA_city.entity.City;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class StreetSaveDto {
    private Long id;

    @NotBlank
    private String name;
    @Min(value = 1, message = "Значение должно быть больше нуля.")
    private Long city;
}
