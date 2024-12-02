package com.springbootacademy.pos_system.service;

import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos_system.dto.request.RequestOrderSaveDto;

import javax.validation.constraints.Max;

public interface OrderService {
    String addOrder(RequestOrderSaveDto requestOrderSaveDto);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, @Max(50) int size);
}
