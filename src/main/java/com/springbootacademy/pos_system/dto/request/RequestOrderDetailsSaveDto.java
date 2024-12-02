package com.springbootacademy.pos_system.dto.request;

import com.springbootacademy.pos_system.entity.Item;
import com.springbootacademy.pos_system.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDto {
    private String itemName;
    private Double quantity;
    private Double amount;
    private int items;
}
