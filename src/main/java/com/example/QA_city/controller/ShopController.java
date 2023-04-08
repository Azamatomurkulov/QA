package com.example.QA_city.controller;

import com.example.QA_city.dto.ShopSaveDto;
import com.example.QA_city.dto.ShopShowDto;
import com.example.QA_city.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    ShopService shopService;

    @GetMapping("/{id}")
    ShopShowDto getShopById(@PathVariable @Min(1) Long id){
        return shopService.findById(id);
    }

    @GetMapping("/all")
    List<ShopShowDto> getAllShops(){
        return shopService.getAllShops();
    }


    @PutMapping("/update/{id}")
    ShopShowDto updateShop(@PathVariable Long id,
                       @RequestBody ShopSaveDto dto){
        return shopService.updateShop(id,dto);
    }

    @PutMapping("/delete/{id}")
    String deleteShopById(@PathVariable Long id){
        return shopService.deleteById(id);
    }

    @PostMapping("/addNewShop")
    Long addNewShop2(@RequestBody ShopSaveDto dto){
        return shopService.addNewShop2(dto);
    }

    @GetMapping("/")
    List<ShopShowDto> findShops(@RequestParam(name = "city",required = false) Long city_id,
                            @RequestParam(name = "street",required = false) Long street_id,
                            @RequestParam(name = "open",required = false) Long is_open){
        return shopService.findShop2(city_id,street_id,is_open);
    }
}
