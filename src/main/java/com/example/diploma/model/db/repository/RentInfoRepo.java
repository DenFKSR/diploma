package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.RentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentInfoRepo extends JpaRepository<RentInfo, Long> {
    Optional<RentInfo> findById(Long id);
    List<RentInfo> findByCarId(Long carId);



//    @Query(value = "select count(ri.id) > 0  from RentInfo ri where ri.car.id = :carId and" +
//            " (ri.startDateRent <= :startDateRent and ri.endDateRent >= :startDateRent) or " +
//            "(ri.startDateRent <= :endDateRent and ri.endDateRent >= :endDateRent)")
//    boolean existsRentByCarId(Long carId, LocalDate startDateRent, LocalDate endDateRent);
//
//    @Query(value = "select count(ri.id) > 0  from RentInfo ri where ri.customer.id = :customerId and" +
//            " (ri.startDateRent <= :startDateRent and ri.endDateRent >= :startDateRent) or " +
//            "(ri.startDateRent <= :endDateRent and ri.endDateRent >= :endDateRent)")
//    boolean existsRentByCustomerId(
//             Long customerId, LocalDate startDateRent, LocalDate endDateRent
//    );
}
