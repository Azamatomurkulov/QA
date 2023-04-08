package com.example.QA_city.service;

import com.example.QA_city.dto.StreetSaveDto;
import com.example.QA_city.dto.StreetShowDto;
import com.example.QA_city.entity.City;
import com.example.QA_city.entity.Street;
import com.example.QA_city.repository.StreetRepository;
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
public class StreetService {

    StreetRepository streetRepository;

    private StreetShowDto streetToDto(Street street){
        StreetShowDto dto = new StreetShowDto();
        dto.setId(street.getId());
        dto.setName(street.getName());
        dto.setNameOfCity(street.getCity().getName());
        return dto;
    }


    public StreetShowDto findById(Long id){
        Street street = streetRepository.findByIdAndRdtIsNull(id);
        return streetToDto(street);
    }

    public List<StreetShowDto> findAllStreets(){
        List<Street> streets = streetRepository.findAllByRdtIsNull();
        List<StreetShowDto> dtos = new ArrayList<>();
        for(Street street: streets){
            StreetShowDto dto = streetToDto(street);
            dtos.add(dto);
        }
        return dtos;
    }

    public String addNewStreet(@Valid StreetSaveDto dto){
        Street street = new Street();
        street.setName(dto.getName());
        street.setCity(new City(dto.getCity()));
        streetRepository.save(street);
        return "The street with name: "+street.getName()+" and ID: "+street.getId()+
                " has been added.";
    }

    public StreetShowDto updateStreet(Long id,StreetSaveDto dto){
        Street street = streetRepository.findByIdAndRdtIsNull(id);
        if(dto.getName()!=null){
            street.setName(dto.getName());
        }
        if(dto.getCity()!=null){
            street.setCity(new City(dto.getCity()));
        }
        streetRepository.save(street);
        return streetToDto(street);
    }

    public String deleteStreetById(Long id){
        Street street = streetRepository.findByIdAndRdtIsNull(id);
        street.setRdt(LocalDateTime.now());
        streetRepository.save(street);
        return "Street with name: "+street.getName()+" has been deleted.";
    }

    public List<StreetShowDto> getStreetsByCityId(Long id){
        List<Street> streets = streetRepository.getAllByCity_Id(id);
        List<StreetShowDto> dtos = new ArrayList<>();

        for(Street street: streets){
            StreetShowDto dto = new StreetShowDto();
          dto.setId(street.getId());
          dto.setName(street.getName());
          dto.setNameOfCity(street.getCity().getName());
            dtos.add(dto);
        }
        return dtos;
    }
}
