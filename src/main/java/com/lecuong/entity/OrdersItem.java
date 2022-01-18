package com.lecuong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersItem extends BaseEntity {

    @Column
    private Long quantity;

    @Column
    private Double price;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
