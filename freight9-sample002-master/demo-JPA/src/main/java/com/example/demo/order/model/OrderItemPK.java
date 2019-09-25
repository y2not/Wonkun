package com.example.demo.order.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class OrderItemPK implements Serializable {

    private Long id;

    @Id
    @Column(name = "productcode", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productcode;
}