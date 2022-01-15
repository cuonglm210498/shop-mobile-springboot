package com.lecuong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column
    private String name;

    @Column
    private String color;

    @Column
    private String batteryCapacity;

    @Column
    private String operationSystem;

    @Column
    private String screenSize;

    @Column
    private String ram;

    @Column
    private String warranty;

    @Column
    private String cpu;

    @Column
    private String memory;

    @Column
    private String thumbnail;

    @Column
    private String realCamera;

    @Column
    private String frontCamera;

    @Column
    private String status;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrdersItem> ordersItems = new ArrayList<>();
}
