package com.example.demo.order.mapper;

import com.example.demo.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    //@Select("select m.id, emp , productcode, productname, qty, price from ordermaster m, orderitem d  where emp = #{emp} and m.id = d.id ")
    List<Order> findAllByEmp(@Param("emp") String emp);

//    @Insert("insert into (emp) values( #{userName} ) ")
//    void insertEmp(@Param("userName") String userName);

    List<Order> search(@Param("emp") String emp);

    List<Order> search2(@Param("emp") String emp,@Param("id") long id );

}
