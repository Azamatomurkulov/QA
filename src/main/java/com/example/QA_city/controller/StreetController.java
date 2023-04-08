package com.example.QA_city.controller;

import com.example.QA_city.dto.StreetSaveDto;
import com.example.QA_city.dto.StreetShowDto;
import com.example.QA_city.service.StreetService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/street")
public class StreetController {

    StreetService streetService;

    @GetMapping("/{id}")
    StreetShowDto getStreetById(@PathVariable @Min(1) Long id){
        return streetService.findById(id);
    }

    @GetMapping("/all")
    List<StreetShowDto> getAllStreets(){
        return streetService.findAllStreets();
    }

    @PostMapping("/addNew")
    String addNewStreet(@RequestBody StreetSaveDto dto){
        return streetService.addNewStreet(dto);
    }

    @PutMapping("/update/{id}")
    StreetShowDto updateStreet(@PathVariable Long id,
                       @RequestBody StreetSaveDto dto){
        return streetService.updateStreet(id,dto);
    }

    @PutMapping("/delete/{id}")
    String deleteStreetById(@PathVariable Long id){
        return streetService.deleteStreetById(id);
    }

    @GetMapping("/city/{id}")
    List<StreetShowDto> getStreetsByCityId(@PathVariable Long id){
        return streetService.getStreetsByCityId(id);
    }
}
