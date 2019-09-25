package com.example.demo.order.repository;

import java.util.List;

import com.example.demo.order.model.City;
import com.example.demo.order.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.OrderBy;

public interface BookRepository extends PagingAndSortingRepository<City, Long> {

    @OrderBy("name desc, id asc")
    List<Book> findByName(String name);

}
