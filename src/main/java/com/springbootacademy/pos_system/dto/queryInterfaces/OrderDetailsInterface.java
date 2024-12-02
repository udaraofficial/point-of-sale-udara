package com.springbootacademy.pos_system.dto.queryInterfaces;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {

    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumber();
    Double getOrderTotal();
    Date getDate();
}
