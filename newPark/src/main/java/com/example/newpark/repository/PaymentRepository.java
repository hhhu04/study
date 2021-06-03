package com.example.newpark.repository;

import com.example.newpark.domain.PaymentLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentLogs, Long> {

}
