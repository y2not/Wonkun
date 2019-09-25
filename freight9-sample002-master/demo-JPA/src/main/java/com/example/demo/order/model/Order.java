package com.example.demo.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "ordermaster", schema = "world")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Order extends AuditModel {

    @Id
    @Column(name = "id",insertable = false, updatable = false, nullable = false)
    @GenericGenerator(name = "sequence_orderid", strategy = "com.example.demo.order.model.OrderIDGenerator")
    @GeneratedValue(generator = "sequence_orderid")
    private Long id;

    private String emp;
    private String createdate;

    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // FetchType.LAZY
    private List<OrderItem> orderItemList;

//    @Transient
//    private Currency currency;

    @Transient
    private int testAttr;


}