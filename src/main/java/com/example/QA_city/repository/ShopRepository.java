package com.example.QA_city.repository;

import com.example.QA_city.entity.City;
import com.example.QA_city.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {

    Shop findByIdAndRdtIsNull(Long id);
    List<Shop> findAllByRdtIsNull();
    List<Shop> findAllByCity_Id(Long id);
    List<Shop> findAllByStreet_Id(Long id);
    List<Shop> findAllByCity_IdAndAndStreet_Id(Long city_id,Long street_id);
}
