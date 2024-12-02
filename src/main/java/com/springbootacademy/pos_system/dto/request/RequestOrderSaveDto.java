package com.springbootacademy.pos_system.dto.request;

import com.springbootacademy.pos_system.entity.Customer;
import com.springbootacademy.pos_system.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDto {

    private int customer;
    private Date date;
    private Double total;
    private boolean activeState;
    private List<RequestOrderDetailsSaveDto> orderDetails;


}
