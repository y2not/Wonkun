package com.example.demo.order.service;

import com.example.demo.order.model.City;
import com.example.demo.order.model.Order;
import com.example.demo.order.repository.CityRepository;
import com.example.demo.order.repository.OrderItemRepository;
import com.example.demo.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class OrderService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderMasterRepository;

    @Autowired
    private JdbcTemplate jdbc;

    public List<Map<String,Object>> testJdbcTemplate() {
        List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM world.ordermaster");
        list.forEach(System.out::println);
        return list;
    }


    @Transactional(readOnly = true)
    public List<City> findByPopulation(int population){
        System.out.println("Service username RequestMapping start!!!");
        return this.cityRepository.findByPopulation(population);
    }

    @Transactional//(propagation = Propagation.REQUIRES_NEW)
    public void saveOrder(Order orderMaster) {

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        orderMaster.setCreatedate(dateTimeFormatter.format(LocalDateTime.now()));

        orderMasterRepository.save(orderMaster);
        System.out.println("========master");

//        for(OrderItem orderItem : orderMaster.getOrderItemList() ) {
//            orderItem.setId(orderMaster.getId());
//            orderItemRepository.save(orderItem);
//        }

        System.out.println("========++++++save end+++++++++++++++++++++++++");
    }



}
