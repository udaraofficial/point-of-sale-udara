package com.springbootacademy.pos_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerUpdateDto {
    private int id;
    private String customerName;
    private String customerAddress;
    private double customerSalary;

}
