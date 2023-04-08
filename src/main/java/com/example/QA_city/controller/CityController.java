package com.example.QA_city.controller;

import com.example.QA_city.dto.CityDto;
import com.example.QA_city.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    CityService cityService;

    @GetMapping("/{id}")
    CityDto getCityById(@PathVariable @Min(1) Long id){
        return cityService.getCityById(id);
    }

    @GetMapping("/all")
    List<CityDto> getAllCities(){
        return cityService.getAllCities();
    }

    @PostMapping("/addNew")
    CityDto addNewCity(@RequestBody CityDto dto){
        return cityService.addNewCity(dto);
    }

    @PutMapping("/update/{id}")
    CityDto updateCity(@PathVariable Long id,
                       @RequestBody CityDto dto){
        return cityService.updateCity(id,dto);
    }

    @PutMapping("/delete/{id}")
    String deleteCityById(@PathVariable Long id){
        return cityService.deleteById(id);
    }


}
