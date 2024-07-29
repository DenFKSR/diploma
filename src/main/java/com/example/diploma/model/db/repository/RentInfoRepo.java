package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.RentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentInfoRepo extends JpaRepository<RentInfo, Long> {
    Optional<RentInfo> findById(Long id);
    Optional<RentInfo> findByEmail(String email);
    List<RentInfo> findByCarId(Long carId);
}
