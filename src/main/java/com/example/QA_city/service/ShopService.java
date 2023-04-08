package com.example.QA_city.service;

import com.example.QA_city.dto.ShopSaveDto;
import com.example.QA_city.dto.ShopShowDto;
import com.example.QA_city.dto.ThDto;
import com.example.QA_city.entity.City;
import com.example.QA_city.entity.Shop;
import com.example.QA_city.entity.Street;
import com.example.QA_city.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Validated
@Service
@AllArgsConstructor
public class ShopService {
    ShopRepository shopRepository;

    private ShopShowDto shopToShopShowDto(Shop shop){
        ShopShowDto dto = new ShopShowDto();
        dto.setId(shop.getId());
        dto.setName(shop.getName());
        dto.setCity(shop.getCity().getName());
        dto.setStreet(shop.getStreet().getName());
        dto.setHouse(shop.getHouse());
        dto.setOpeningTime(shop.getOpeningTime());
        dto.setClosingTime(shop.getClosingTime());
        return dto;
    }

    public ShopShowDto findById(Long id) {
        Shop shop = shopRepository.findByIdAndRdtIsNull(id);
        return shopToShopShowDto(shop);
    }

    public List<ShopShowDto> getAllShops() {
        List<Shop> shops = shopRepository.findAllByRdtIsNull();
        List<ShopShowDto> dtos = new ArrayList<>();
        for (Shop shop : shops) {
            ShopShowDto dto = shopToShopShowDto(shop);
            dtos.add(dto);
        }
        return dtos;
    }


    public ShopShowDto updateShop(Long id, ShopSaveDto dto) {
        Shop shop = shopRepository.findByIdAndRdtIsNull(id);
        if (dto.getName() != null) {
            shop.setName(dto.getName());
        }
        if (dto.getCityId() != null) {
            shop.setCity(new City(dto.getCityId()));
        }
        if (dto.getStreetId() != null) {
            shop.setStreet(new Street(dto.getStreetId()));
        }
        if (dto.getHouse() != null) {
            shop.setHouse(dto.getHouse());
        }
        if (dto.getOpeningTime() != null) {
            shop.setOpeningTime(dto.getOpeningTime());
        }
        if (dto.getClosingTime() != null) {
            shop.setClosingTime(dto.getClosingTime());
        }
        shopRepository.save(shop);
        return shopToShopShowDto(shop);
    }

    public String deleteById(Long id) {
        Shop shop = shopRepository.findByIdAndRdtIsNull(id);
        shop.setRdt(LocalDateTime.now());
        shopRepository.save(shop);
        return "The shop: " + shop.getName() + " has been deleted";
    }

    public Long addNewShop2(@Valid ShopSaveDto dto) {
        Shop shop = new Shop();
        shop.setName(dto.getName());
        shop.setCity(new City(dto.getCityId()));
        shop.setStreet(new Street(dto.getStreetId()));
        shop.setHouse(dto.getHouse());
        shop.setOpeningTime(dto.getOpeningTime());
        shop.setClosingTime(dto.getClosingTime());
        return shopRepository.save(shop).getId();
    }

    private Long is_open(Shop shop) {
        LocalTime currentTime = LocalTime.now();
        if (shop.getOpeningTime().isBefore(currentTime) && shop.getClosingTime().isAfter(currentTime)) {
            return 1l;
        } else return 0l;
    }

    public List<ShopShowDto> findShop2(Long city, Long street, Long open) {
        List<ShopShowDto> dtos = new ArrayList<>();
        if (street == null && city == null) {
            List<Shop> shops = shopRepository.findAll();
            if (open != null && open == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (open != null && open == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else
                dtos = new ArrayList<>(getAllShops());


        } else if (street == null) {
            List<Shop> shops = shopRepository.findAllByCity_Id(city);
            if (open != null && open == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (open != null && open == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }


            }else   for (Shop shop : shops) {
                dtos.add(shopToShopShowDto(shop));
            }
        } else if (city == null) {
            List<Shop> shops = shopRepository.findAllByStreet_Id(street);
            if (open != null && open == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (open != null && open == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }

            } else
                for (Shop shop : shops) {
                    dtos.add(shopToShopShowDto(shop));
                }
        }
            else {
                List<Shop> shops = shopRepository.findAllByCity_IdAndAndStreet_Id(city, street);
                if (open != null && open == 1) {
                    for (Shop shop : shops) {
                        if (is_open(shop) == 1) {
                            dtos.add(shopToShopShowDto(shop));
                        }
                    }
                }  else if (open != null && open == 0) {
                    for (Shop shop : shops) {
                        if (is_open(shop) == 0) {
                            dtos.add(shopToShopShowDto(shop));
                        }
                    }

                }else
                    for (Shop shop : shops) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
        return dtos;
    }

    public List<ShopShowDto> findShop3(ThDto dto) {
        List<ShopShowDto> dtos = new ArrayList<>();
        if (dto.getStreet_id() == null && dto.getCity_id() == null) {
            List<Shop> shops = shopRepository.findAll();
            if (dto.getIs_open() != null && dto.getIs_open() == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (dto.getIs_open() != null && dto.getIs_open() == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else
                dtos = new ArrayList<>(getAllShops());


        } else if (dto.getStreet_id() == null) {
            List<Shop> shops = shopRepository.findAllByCity_Id(dto.getCity_id());
            if (dto.getIs_open() != null && dto.getIs_open() == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (dto.getIs_open() != null && dto.getIs_open() == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }


            }else   for (Shop shop : shops) {
                dtos.add(shopToShopShowDto(shop));
            }
        } else if (dto.getCity_id() == null) {
            List<Shop> shops = shopRepository.findAllByStreet_Id(dto.getStreet_id());
            if (dto.getIs_open() != null && dto.getIs_open() == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            } else if (dto.getIs_open() != null && dto.getIs_open() == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }

            } else
                for (Shop shop : shops) {
                    dtos.add(shopToShopShowDto(shop));
                }
        }
        else {
            List<Shop> shops = shopRepository.findAllByCity_IdAndAndStreet_Id(dto.getCity_id(), dto.getStreet_id());
            if (dto.getIs_open() != null && dto.getIs_open() == 1) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 1) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }
            }  else if (dto.getIs_open() != null && dto.getIs_open() == 0) {
                for (Shop shop : shops) {
                    if (is_open(shop) == 0) {
                        dtos.add(shopToShopShowDto(shop));
                    }
                }

            }else
                for (Shop shop : shops) {
                    dtos.add(shopToShopShowDto(shop));
                }
        }
        return dtos;
    }
}