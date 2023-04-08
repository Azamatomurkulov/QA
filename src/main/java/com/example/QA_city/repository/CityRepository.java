package com.example.QA_city.repository;

import com.example.QA_city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    City findByIdAndRdtIsNull(Long id);
    List<City> findAllByRdtIsNull();
}
