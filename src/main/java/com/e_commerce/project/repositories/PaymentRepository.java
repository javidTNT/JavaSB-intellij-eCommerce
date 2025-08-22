package com.e_commerce.project.repositories;

import com.e_commerce.project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional query methods can be defined here if needed
}
