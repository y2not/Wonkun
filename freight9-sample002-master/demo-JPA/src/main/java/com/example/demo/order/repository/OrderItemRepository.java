package com.example.demo.order.repository;

import com.example.demo.order.model.OrderItem;
import com.example.demo.order.model.OrderItemPK;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderItemRepository extends PagingAndSortingRepository <OrderItem, OrderItemPK> {

    List<OrderItem> findByProductname(String productname);

    //    @Query(value="SELECT id, qty, price FROM orderitem where id=:id ")
//    List<OrderItem> findByOrderIdAndNameNamedParams(@Param("id") long id);


}
