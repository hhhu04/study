package com.example.newpark.repository;

import com.example.newpark.entity.PaymentLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentLogs, Long> {
    List<PaymentLogs> findAll();



}
