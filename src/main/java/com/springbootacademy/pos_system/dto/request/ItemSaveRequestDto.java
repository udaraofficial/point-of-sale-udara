package com.springbootacademy.pos_system.dto.request;

import com.springbootacademy.pos_system.entity.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemSaveRequestDto {

    private String itemName;
    private double balanceQty;
    private MeasuringType measuringType;
    private double supplierPrice;
    private double sellingPrice;

}

