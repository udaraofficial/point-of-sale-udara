package com.springbootacademy.pos_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDto {

    private int id;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList contactNumber;
    private String nic;
    private boolean active;
}
