package com.springbootacademy.pos_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data

// Item Details
public class Item {

    @Id
    @Column(name = "item_id" , length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name" , length = 100 , nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_type", length = 100 , nullable = false)
    private MeasuringType measuringType;

    @Column(name = "balacnce_qty" ,length = 100 , nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price" , length = 100 , nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price" , length = 100 , nullable = false)
    private double sellingPrice;

    @Column(name = "active_state" , columnDefinition = "TINYINT default 0")
    private boolean activeState;

    @OneToMany(mappedBy="items")
    private Set<OrderDetails> orderDetails;

}
