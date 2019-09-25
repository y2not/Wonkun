package com.example.demo.order.repository;

import com.example.demo.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    Page<Order> findById(Long orderId, Pageable pageable);


}
