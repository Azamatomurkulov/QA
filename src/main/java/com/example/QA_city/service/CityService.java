package com.example.QA_city.service;

import com.example.QA_city.dto.CityDto;
import com.example.QA_city.entity.City;
import com.example.QA_city.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Validated
@Service
@AllArgsConstructor
public class CityService {
    CityRepository cityRepository;

    private CityDto cityToDto(City city){
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    public CityDto getCityById(Long id){
        City city = new City();
        try{
            city = cityRepository.findByIdAndRdtIsNull(id);
        }catch (NullPointerException e){
            System.out.println("Город с таким айди не найден");
        }

        return cityToDto(city);
    }

    public List<CityDto> getAllCities(){
        List<City> cities = cityRepository.findAllByRdtIsNull();
        List<CityDto> dtos = new ArrayList<>();
        for(City city: cities){
            CityDto dto = cityToDto(city);
            dtos.add(dto);
        }
        return dtos;
    }

    public CityDto addNewCity(@Valid CityDto dto){
        City city = new City();
        city.setName(dto.getName());
        city = cityRepository.save(city);
        dto.setId(city.getId());
        return dto;
    }
    public CityDto updateCity(Long id, CityDto dto){
        City city = cityRepository.findByIdAndRdtIsNull(id);
        city.setName(dto.getName());
        cityRepository.save(city);
        dto.setId(city.getId());
        return dto;
    }

    public String deleteById(Long id){
        City city = cityRepository.findByIdAndRdtIsNull(id);
        city.setRdt(LocalDateTime.now());
        cityRepository.save(city);
        return "The city: "+city.getName()+" has been deleted";
    }


}
