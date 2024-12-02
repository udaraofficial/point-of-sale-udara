package com.springbootacademy.pos_system.service.impl;

import com.springbootacademy.pos_system.dto.CustomerDto;
import com.springbootacademy.pos_system.dto.request.CustomerUpdateDto;
import com.springbootacademy.pos_system.entity.Customer;
import com.springbootacademy.pos_system.exception.NotFoundException;
import com.springbootacademy.pos_system.repo.CustomerRepo;
import com.springbootacademy.pos_system.service.CustomerService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepo customerRepo;

    // Save Customer
    @Override
    public String saveCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepo.save(customer);
        return customer.getCustomerName() + " saved successfully";
    }
    // Update Customer
    @Override
    public String updateCustomer(CustomerUpdateDto customerUpdateDto) {
        if(customerRepo.existsById(customerUpdateDto.getId())){
            Customer customer = modelMapper.map(customerUpdateDto, Customer.class);
            customerRepo.save(customer);
            return customerUpdateDto.getCustomerName() + " updated successfully";
        }else{
            throw new NotFoundException("Customer not found that Id");
        }

    }
    // Select All Customer Details
    @Override
    public List<CustomerDto> getAllCustomers() {

        List getAllCustomers = modelMapper.map(customerRepo.findAll(), List.class);
        if (getAllCustomers.size() > 0) {
            List<CustomerDto> customerDtosList = new ArrayList<>();
            for (Object customer : getAllCustomers) {
                customerDtosList.add(modelMapper.map(customer, CustomerDto.class));
            }
            return customerDtosList;
        } else {
            throw new NotFoundException("No Customers found");
        }
    }
    // Select specific Customer
    @Override
    public CustomerDto getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDto customerDto  = modelMapper.map(customer, CustomerDto.class);
            return customerDto;
        } else {
            throw new NotFoundException("No Customer found that id ");
        }

    }
    // Delete Customer
    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return customerId + " deleted successfully";
        }else{
            throw new NotFoundException("No Customer Found");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomersByActiveState(boolean activeState) {
        List <Customer> customers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDto> customerDtosList = new ArrayList<>();
        for (Customer customer: customers ) {
            customerDtosList.add(modelMapper.map(customer, CustomerDto.class));
        }
        return customerDtosList;
    }

}
