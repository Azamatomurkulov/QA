package com.example.QA_city.thcontroller;

import com.example.QA_city.dto.*;
import com.example.QA_city.service.CityService;
import com.example.QA_city.service.ShopService;
import com.example.QA_city.service.StreetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@AllArgsConstructor
public class ThController {
    CityService cityService;
    ShopService shopService;
    StreetService streetService;

    @GetMapping("/main")
    public String main()throws Exception{
        return "findShop";
    }

    @GetMapping("/allShops")
    public String allShops(Model model)throws Exception{
        List<ShopShowDto> shops = shopService.getAllShops();
        model.addAttribute("shops",shops);
        return "shopList";

    }
    @GetMapping("/allStreets")
    public String allStreets(Model model)throws Exception{
        List<StreetShowDto> streets = streetService.findAllStreets();
        model.addAttribute("streets",streets);
        return "streetList";
    }
    @GetMapping("/allCities")
    public String allCities(Model model)throws Exception{
        List<CityDto> cities = cityService.getAllCities();
        model.addAttribute("cities",cities);
        return "citiesList";
    }
    @GetMapping("/search")
    public String search(@ModelAttribute("dto") ThDto dto)throws Exception{
        return "searchPage";
    }


    @GetMapping("/search/shop")
    public String findShop(@ModelAttribute("dto") ThDto dto, Model model) throws Exception {
        List<ShopShowDto> shops = shopService.findShop3(dto);
        model.addAttribute("shops", shops);
        return "searchRes";
    }
    @GetMapping("/add")
    public String addShop(@ModelAttribute("dto") ShopSaveDto dto)throws Exception{
        return "addNewShop";
    }

    @GetMapping("/add/newShop")
    public String addNewShop(@ModelAttribute("dto") ShopSaveDto dto, Model model)throws Exception{
        Long id = shopService.addNewShop2(dto);
        model.addAttribute("id",id);
        return "ShopAdded";
    }
}
