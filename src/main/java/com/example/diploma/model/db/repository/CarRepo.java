package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car, Long> {

    Optional<Car> findByRegisterNumber(String number);
    @Query("select c from Car c where (:brand is null or lower(c.brand) = lower(:brand)) and (:transmission is null or lower(c.transmission) = lower(:transmission)) and (:year is null or c.year = :year) and (:price is null or c.price = :price) and (:bodyType is null or lower(c.bodyType) = lower(:bodyType))  and (:id is null or c.id = :id) and (:registerNumber is null or lower(c.registerNumber) = lower(:registerNumber))")
    List<Car> findByFilters( String brand, String transmission, Integer year, Double price, String bodyType, Long id,String registerNumber);

    @Query("select c from Car c where c.id = :id")
    Optional<Car> findById(Long id);

    @Query("SELECT c.brand, c.model, c.price, c.transmission, c.bodyType, c.engineType, c.fuelСonsumption, c.seatsAmount FROM Car c WHERE c.id = :carId")
    String findCarDetailsByCarId(@Param("carId") Long carId);// переделать тип метода в какой нибудь стринговый массив, так как он возвращает
    // массив строк из столбцов

    @Transactional
    @Modifying
    @Query("delete from Car с where с.id = ?1")
    void deleteCarById(Long id);
}