package com.example.demo.order.repository;

import com.example.demo.order.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByUsername(String username);

}
