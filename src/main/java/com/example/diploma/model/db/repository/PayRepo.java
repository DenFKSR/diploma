package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepo extends JpaRepository<Payment, Long> {
}
