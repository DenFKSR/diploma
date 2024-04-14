package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car, Long> {

    Optional<Car> findByRegisterNumber(String number);
    @Query("select c from Car c where (:brand is null or lower(c.brand) = lower(:brand)) and (:transmission is null or lower(c.transmission) = lower(:transmission)) and (:year is null or c.year = :year) and (:price is null or c.price = :price) and (:bodyType is null or lower(c.bodyType) = lower(:bodyType))")
    List<Car> findByFilters( String brand, String transmission, Integer year, Double price, String bodyType);

    Optional<Car> findById(Long id);
}