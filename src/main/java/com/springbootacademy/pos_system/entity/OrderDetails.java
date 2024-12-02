package com.springbootacademy.pos_system.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "order_details")
@TypeDefs(
        @TypeDef(name = "json",typeClass = JsonType.class)
)

@AllArgsConstructor
@NoArgsConstructor
@Data
// Customer order details
public class OrderDetails {

    @Id
    @Column(name = "order_details_id" ,length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    @Column(name = "item_name" ,length = 100 , nullable = false)
    private String itemName;

    @Column(name = "quantity", length = 100 , nullable = false)
    private Double quantity;

    @Column(name = "amount" , nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order orders;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items;


}
