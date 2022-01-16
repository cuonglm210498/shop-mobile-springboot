package com.lecuong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseEntity {

    @Column
    private String shippingAddress;

    @Column
    private String status;

    @Column
    private LocalDate receivingDate;

    @Column
    private String recipient;

    @Column
    private String phoneNumber;

    @Column
    private Double totalAmount;

    @OneToMany(mappedBy = "orders")
    private List<OrdersItem> ordersItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
