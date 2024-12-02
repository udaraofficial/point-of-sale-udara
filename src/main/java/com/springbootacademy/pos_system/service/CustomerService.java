package com.springbootacademy.pos_system.service;

import com.springbootacademy.pos_system.dto.CustomerDto;
import com.springbootacademy.pos_system.dto.request.CustomerUpdateDto;

import java.util.List;

public interface CustomerService {

    String saveCustomer(CustomerDto customerDto);

    String updateCustomer(CustomerUpdateDto customerUpdateDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(int customerId);

    String deleteCustomer(int customerId);

    List<CustomerDto> getAllCustomersByActiveState(boolean activeState);
}
