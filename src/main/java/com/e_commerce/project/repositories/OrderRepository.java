package com.e_commerce.project.repositories;

import com.e_commerce.project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
