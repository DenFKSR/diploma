package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.RentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentInfoRepo extends JpaRepository<RentInfo, Long> {
    @Query(value = "select count(ri.id) > 0  from RentInfo ri where ri.car.id = :carId and" +
            " (ri.startDateRent <= :startDateRent and ri.endDateRent >= :startDateRent) or " +
            "(ri.startDateRent <= :endDateRent and ri.endDateRent >= :endDateRent)")
    boolean existsRentByCarId(Long carId, LocalDate startDateRent, LocalDate endDateRent);

    @Query(value = "select count(ri.id) > 0  from RentInfo ri where ri.customer.id = :customerId and" +
            " (ri.startDateRent <= :startDateRent and ri.endDateRent >= :startDateRent) or " +
            "(ri.startDateRent <= :endDateRent and ri.endDateRent >= :endDateRent)")
    boolean existsRentByCustomerId(
             Long customerId, LocalDate startDateRent, LocalDate endDateRent
    );
}
