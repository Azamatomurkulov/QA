package com.example.QA_city.repository;

import com.example.QA_city.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street,Long> {

    Street findByIdAndRdtIsNull(Long id);
    List<Street> findAllByRdtIsNull();

    List<Street> getAllByCity_Id(Long id);
}
