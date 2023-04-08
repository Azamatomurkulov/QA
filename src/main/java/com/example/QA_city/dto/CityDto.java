package com.example.QA_city.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class CityDto {
    private Long id;
    @NotBlank
    private String name;
}
